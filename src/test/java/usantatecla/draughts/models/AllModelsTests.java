package usantatecla.draughts.models;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	GameMovementTest.class,
	GamePiecesTest.class,
	CoordinateParametrizedTest.class,
	CoordinateWithoutParametrizedTest.class,
	TurnParameterizedTest.class,
	PieceParameterizedTest.class
})
public class AllModelsTests {
}
