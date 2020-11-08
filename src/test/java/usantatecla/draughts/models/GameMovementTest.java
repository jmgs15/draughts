package usantatecla.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameMovementTest {

	Game game;
	
	@Test
	public void testGivenBlockedPawnThenReturnTrue() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				" n   n  ",
				"  n n   ",
				"   b    ",
				"        ",
				"        ",
				"        ").build();
		assertTrue(this.game.isBlocked());
	}
	
	@Test
	public void testGivenUnblockedPawnThenReturnFalse() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				" n      ",
				"  n n   ",
				"   b    ",
				"  n n   ",
				" n   n  ",
				"        ").build();
		assertFalse(this.game.isBlocked());
	}
	
	@Test
	public void testGivenBlockedDraughtThenReturnTrue() {
		this.game = new GameBuilder().rows(
				"        ",
				"  n     ",
				" n      ",
				"B       ",
				" n      ",
				"  n     ",
				"        ",
				"        ").build();
		assertTrue(this.game.isBlocked());
	}
	
	@Test
	public void testGivenUnblockedDraughtThenReturnFalse() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				"     n  ",
				"      n ",
				"       B",
				"      n ",
				"        ",
				"        ").build();
		assertFalse(this.game.isBlocked());
	}
	
	@Test
	public void testWhenPairMoveAndMoveVerticalThenError() {
		this.game = new GameBuilder().rows(
				"        ",
				"      n ",
				"        ",
				"      n ",
				"        ",
				"      n ",
				"     b  ",
				"        ").build();
		Error error = move(coordinate(6, 5), coordinate(4, 7), coordinate(3, 7));
		assertEquals(error, Error.NOT_DIAGONAL);
	}
	
	@Test
	public void testWhenPairMoveAndErrorThenRestorePiecesCorrectly() {
		this.game = new GameBuilder().rows(
				"       n",
				"      n ",
				"        ",
				"      n ",
				"        ",
				"      N ",
				"     b  ",
				"        ").build();
		Error error = move(coordinate(6, 5), coordinate(4, 7), coordinate(2, 5), coordinate(0, 7));
		assertEquals(error, Error.NOT_EMPTY_TARGET);
		assertTrue(piece(coordinate(3, 6)) instanceof Pawn);
		assertTrue(piece(coordinate(5, 6)) instanceof Draught);
	}
	
	@Test
	public void testWhenMoreThanTwoMovementsWithoutEatingInEachMovementThenError() {
		this.game = new GameBuilder().rows(
				"       n",
				"      n ",
				"        ",
				"      n ",
				"        ",
				"      N ",
				"     b  ",
				"        ").build();
		Error error = move(coordinate(6, 5), coordinate(4, 7), coordinate(2, 5), coordinate(1, 4));
		assertEquals(error, Error.TOO_MUCH_JUMPS);
	}
	
	@Test
	public void testWhenMoreThanTwoMovementsWithoutEatingThenError() {
		this.game = new GameBuilder().rows(
				"       n",
				"      n ",
				"        ",
				"      n ",
				"        ",
				"      N ",
				"     b  ",
				"        ").build();
		Error error = move(coordinate(6, 5), coordinate(5, 4), coordinate(4, 3));
		assertEquals(error, Error.TOO_MUCH_JUMPS);
	}
	
	@Test
	public void testWhenEatingMultiplePiecesReachingLimitThenConvertIntoDraught() {
		this.game = new GameBuilder().rows(
				"        ",
				"  n     ",
				"        ",
				"    n   ",
				"        ",
				"      N ",
				"       b",
				"        ").build();
		Error error = move(coordinate(6, 7), coordinate(4, 5), coordinate(2, 3), coordinate(0, 1));
		assertNull(error);
		assertTrue(piece(coordinate(0, 1)) instanceof Draught);
		assertNull(piece(coordinate(1, 2)));
	}
	
	@Test
	public void testWhenMovingBackWhitesThenError() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				"        ",
				"        ",
				"        ",
				"    n N ",
				"       b",
				"        ").build();
		Error error = move(coordinate(6, 7), coordinate(4, 5), coordinate(6, 3));
		assertEquals(error, Error.NOT_ADVANCED);
	}
	
	@Test
	public void testWhenMovingBackBlacksThenError() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				"        ",
				"        ",
				"        ",
				"    n   ",
				"       b",
				"        ").build();
		this.game.cancel();
		Error error = move(coordinate(5, 4), coordinate(4, 3));
		assertEquals(error, Error.NOT_ADVANCED);
	}
	
	@Test
	public void testWhenMovingOppositePieceThenError() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				"        ",
				"        ",
				"        ",
				"        ",
				"       n",
				"        ").build();
		Error error = move(coordinate(6, 7), coordinate(5, 6));
		assertEquals(error, Error.OPPOSITE_PIECE);
	}
	
	@Test
	public void testWhenSelectingEmptyOriginThenReturnError() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				"        ",
				"        ",
				"        ",
				"        ",
				"       n",
				"        ").build();
		Error error = move(coordinate(3, 0), coordinate(2, 1));
		assertEquals(error, Error.EMPTY_ORIGIN);
	}
	
	@Test
	public void testWhenMovingDoubleThenError() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				"        ",
				"        ",
				"        ",
				"        ",
				"       b",
				"        ").build();
		Error error = move(coordinate(6, 7), coordinate(4, 5));
		assertEquals(error, Error.WITHOUT_EATING);
	}
	
	@Test
	public void testWhenMovingTripleThenError() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				"        ",
				"        ",
				"        ",
				"        ",
				"       b",
				"        ").build();
		Error error = move(coordinate(6, 7), coordinate(3, 4));
		assertEquals(error, Error.TOO_MUCH_ADVANCED);
	}
	
	@Test
	public void testWhenEatingColleagueThenError() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				"        ",
				"        ",
				"        ",
				"      b ",
				"       b",
				"        ").build();
		Error error = move(coordinate(6, 7), coordinate(4, 5));
		assertEquals(error, Error.COLLEAGUE_EATING);
	}
	
	@Test
	public void testWhenPawnEatingTwoThenError() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				"        ",
				"        ",
				"     n  ",
				"      n ",
				"       b",
				"        ").build();
		Error error = move(coordinate(6, 7), coordinate(3, 4));
		assertEquals(error, Error.TOO_MUCH_ADVANCED);
	}
	
	@Test
	public void testWhenDraughtEatingTwoThenError() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				"        ",
				"        ",
				"     n  ",
				"      n ",
				"       B",
				"        ").build();
		Error error = move(coordinate(6, 7), coordinate(3, 4));
		assertEquals(error, Error.TOO_MUCH_EATINGS);
	}
	
	@Test
	public void testWhenMovingDraughtVerticalThenError() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				"        ",
				"        ",
				"     n  ",
				"      n ",
				"       B",
				"        ").build();
		Error error = move(coordinate(6, 7), coordinate(5, 7));
		assertEquals(error, Error.NOT_DIAGONAL);
	}
	
	@Test
	public void testWhenMovingPawnHorizontalThenError() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				"        ",
				"        ",
				"     n  ",
				"      n ",
				"       b",
				"        ").build();
		Error error = move(coordinate(6, 7), coordinate(6, 6));
		assertEquals(error, Error.NOT_DIAGONAL);
	}
	
	private Coordinate coordinate(int row, int column) {
		return new Coordinate(row, column);
	}
	
	private Piece piece(Coordinate coordinate) {
		return this.game.getPiece(coordinate);
	}
	
	private Error move(Coordinate... coordinates) {
		return this.game.move(coordinates);
	}
}
