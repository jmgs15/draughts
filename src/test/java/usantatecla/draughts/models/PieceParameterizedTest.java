package usantatecla.draughts.models;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PieceParameterizedTest {

	Piece piece;
	
	Coordinate coordinateToTestLimit;
	boolean expectedIsLimit;
	
	Coordinate coordinateOriginAdvance;
	Coordinate coordinateTargetAdvance;
	boolean expectedIsAdvancing;
	
	List<Piece> betweenDiagonalPieces;
	int pair;
	Coordinate[] coordinatesCorrectMovement;
	Error expectedIsCorrectMovement;
	
	public PieceParameterizedTest(Piece piece, Coordinate coordinateToTestLimit, boolean expectedIsLimit,
			Coordinate coordinateOriginAdvance, Coordinate coordinateTargetAdvance, boolean expectedIsAdvancing,
			List<Piece> betweenDiagonalPieces, int pair, Coordinate[] coordinatesCorrectMovement,
			Error expectedIsCorrectMovement) {
		this.piece = piece;
		this.coordinateToTestLimit = coordinateToTestLimit;
		this.expectedIsLimit = expectedIsLimit;
		this.coordinateOriginAdvance = coordinateOriginAdvance;
		this.coordinateTargetAdvance = coordinateTargetAdvance;
		this.expectedIsAdvancing = expectedIsAdvancing;
		this.betweenDiagonalPieces = betweenDiagonalPieces;
		this.pair = pair;
		this.coordinatesCorrectMovement = coordinatesCorrectMovement;
		this.expectedIsCorrectMovement = expectedIsCorrectMovement;
	}
	
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{ pawn(white()),
				coordinate(0, 0), true,
				coordinate(5, 4), coordinate(4, 5), true,
				Arrays.asList(pawn(black())), 0, new Coordinate[] {coordinate(5, 4), coordinate(3, 6)}, null}
		});
	}
	
	@Test
	public void testIsLimit() {
		assertEquals(this.piece.isLimit(coordinateToTestLimit), expectedIsLimit);
	}
	
	@Test
	public void testIsAdvanced() {
		assertEquals(this.piece.isAdvanced(coordinateOriginAdvance, coordinateTargetAdvance), expectedIsAdvancing);
	}
	
	@Test
	public void testIsCorrectMovement() {
		assertEquals(this.piece.isCorrectMovement(betweenDiagonalPieces, pair, coordinatesCorrectMovement), expectedIsCorrectMovement);
	}
	
	private static Draught draught(Color color) {
		return new Draught(color);
	}
	
	private static Pawn pawn(Color color) {
		return new Pawn(color);
	}
	
	private static Coordinate coordinate(int row, int column) {
		return new Coordinate(row, column);
	}
	
	private static Color white() {
		return Color.WHITE;
	}
	
	private static Color black() {
		return Color.BLACK;
	}
}
