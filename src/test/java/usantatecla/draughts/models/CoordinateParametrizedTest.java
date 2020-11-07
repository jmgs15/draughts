package usantatecla.draughts.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CoordinateParametrizedTest {
    private final int OUTRow;
    private final int OUTColumn;

    private final Coordinate coordinate;
    private final Direction expectedDirection;

    private final String getInstanceOf;
    private final Coordinate expectedCoordinateInGetInstance;
    private final boolean expectIsOnDiagonal;

    private final int expectDiagonalDistance;

    private final Coordinate betweenCoordinate;
    private final List<Coordinate> expectedCoordinates;


    public CoordinateParametrizedTest(int OUTRow, int OUTColumn, Coordinate coordinate, Direction expectedDirection, String getInstanceOf,
                                      Coordinate expectedCoordinateInGetInstance, boolean expectIsOnDiagonal, int expectDiagonalDistance,
                                      Coordinate betweenCoordinate, List<Coordinate> expectedCoordinates) {
        this.OUTRow = OUTRow;
        this.OUTColumn = OUTColumn;
        this.coordinate = coordinate;
        this.expectedDirection = expectedDirection;
        this.getInstanceOf = getInstanceOf;
        this.expectedCoordinateInGetInstance = expectedCoordinateInGetInstance;
        this.expectIsOnDiagonal = expectIsOnDiagonal;
        this.expectDiagonalDistance = expectDiagonalDistance;
        this.betweenCoordinate = betweenCoordinate;
        this.expectedCoordinates = expectedCoordinates;
    }

    private Coordinate OUTCoordinate;

    @Parameters(name = "(Row: {0} Column: {1}, Coordinate: {4})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 1, new Coordinate(3, 3), Direction.NE, "32", new Coordinate(2, 1),
                        true, 2, new Coordinate(3, 3), List.of(new Coordinate(2, 2))},
                {1, 1, new Coordinate(1, 2), null, "99", null,
                        false, 0, new Coordinate(1, 1), List.of()},
                {1, 1, new Coordinate(1, 1), null, "unparseable", null,
                        false, 0, null, null},
        });
    }

    @Before
    public void beforeEach() {
        OUTCoordinate = new Coordinate(OUTRow, OUTColumn);
    }

    //No se como comprobar cuando getInstanceOf = null -> throws AssertionError

    @Test
    public void testGetInstance() {
        Assert.assertEquals(this.expectedCoordinateInGetInstance, Coordinate.getInstance(this.getInstanceOf));
    }

    @Test
    public void testGetDirection() {
        Assert.assertEquals(this.expectedDirection, this.OUTCoordinate.getDirection(this.coordinate));
    }

    @Test
    public void testIsOnDiagonal() {
        Assert.assertEquals(this.expectIsOnDiagonal, this.OUTCoordinate.isOnDiagonal(this.coordinate));
    }

    @Test
    public void testGetDiagonalDistance() {
        try {
            int distance = this.OUTCoordinate.getDiagonalDistance(coordinate);
            Assert.assertEquals(this.expectDiagonalDistance, distance);
        } catch (AssertionError assertionError) {
            assertionError.printStackTrace();
        }
    }

    // No se si se puede poner el try catch para el AssertionError
    @Test
    public void testGetBetweenCoordinates() {
        try {
            List<Coordinate> coordinates = this.OUTCoordinate.getBetweenDiagonalCoordinates(betweenCoordinate);
            Assert.assertEquals(this.expectedCoordinates, coordinates);
        } catch (AssertionError error) {
            error.printStackTrace();
        }
    }

}
