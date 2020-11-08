package usantatecla.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GameMovementTest {

	Game game;
	
	//TODO: ERRORES DE MOVIMIENTO DE PAWN Y DRAUGHT
	//TODO: PROBAR TOO_MUCH_JUMPS
	/*
	 * Error!!! No te entiendo: <d><d>{,<d><d>}[0-2] ✔
	 * Error!!! No es una coordenada del tablero ✔
	 * Error!!! No hay ficha que mover ✔
	 * Error!!! No es una de tus fichas ✔
	 * Error!!! No vas en diagonal
	 * Error!!! No está vacío el destino ✔
	 * Error!!! No comes contrarias
	 * Error!!! No se puede comer tantas en un movimiento
	 * Error!!! No avanzas ✔
	 * Error!!! No respetas la distancia
	 * Error!!! No se puede comer tantas en un salto
	 */
	
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
	public void testWhenErrorMovingThenRestorePiecesCorrectly() {
		this.game = new GameBuilder().rows(
				"       n",
				"      n ",
				"        ",
				"      n ",
				"        ",
				"      N ",
				"     b  ",
				"        ").build();
		Error error = this.game.move(coordinate(6, 5), coordinate(4, 7), coordinate(0, 7));
		assertEquals(error, Error.NOT_EMPTY_TARGET);
		assertTrue(piece(coordinate(3, 6)) instanceof Pawn);
		assertTrue(piece(coordinate(5, 6)) instanceof Draught);
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
		Error error = this.game.move(coordinate(6, 7), coordinate(4, 5), coordinate(2, 3), coordinate(0, 1));
		assertNull(error);
		assertTrue(piece(coordinate(0, 1)) instanceof Draught);
		assertNull(piece(coordinate(1, 2)));
	}
	
	@Test
	public void testWhenMovingBackThenError() {
		this.game = new GameBuilder().rows(
				"        ",
				"        ",
				"        ",
				"        ",
				"        ",
				"    n N ",
				"       b",
				"        ").build();
		Error error = this.game.move(coordinate(6, 7), coordinate(4, 5), coordinate(6, 3));
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
		Error error = this.game.move(coordinate(6, 7), coordinate(5, 6));
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
		Error error = this.game.move(coordinate(3, 0), coordinate(2, 1));
		assertEquals(error, Error.EMPTY_ORIGIN);
	}
	
	private Coordinate coordinate(int row, int column) {
		return new Coordinate(row, column);
	}
	
	private Piece piece(Coordinate coordinate) {
		return this.game.getPiece(coordinate);
	}
}
