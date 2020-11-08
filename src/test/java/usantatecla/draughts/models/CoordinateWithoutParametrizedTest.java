package usantatecla.draughts.models;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoordinateWithoutParametrizedTest {
    private Coordinate coordinate;
    private Coordinate onNEDiagonalCoordinate;
    private final int row = 4;
    private final int column = 5;

    @Before
    public void beforeAll() {
        this.coordinate = new Coordinate(this.row, this.column);
        this.onNEDiagonalCoordinate = new Coordinate(this.row + 2, this.column + 2);
    }

    @Test(expected = AssertionError.class)
    public void testGetInstanceWithNullThrowsError() {
        Coordinate.getInstance(null);
    }

    @Test
    public void testGetInstanceWithWrongCoordinate() {
        String invalidCoordinateInput = "99";
        Assert.assertNull(Coordinate.getInstance(invalidCoordinateInput));
    }

    @Test
    public void testGetInstanceWithUnparseableInput() {
        Assert.assertNull(Coordinate.getInstance("unparseable"));
    }

    @Test
    public void testGetInstanceShouldReturnCoordinate() {
        Assert.assertEquals(coordinate, Coordinate.getInstance("56"));
    }

    @Test(expected = AssertionError.class)
    public void testGetDirectionWithNullInputValue() {
        this.coordinate.getDirection(null);
    }

    @Test
    public void testGetDirectionWithInvalidInput() {
        this.coordinate.getDirection(new Coordinate(this.row + 1, this.column));
        Assert.assertNull(this.coordinate.getDirection(new Coordinate(row, column)));
    }

    @Test
    public void testGetDirection() {
        Assert.assertEquals(Direction.SW, this.coordinate.getDirection(new Coordinate(this.row - 1, this.column - 1)));
    }

    @Test
    public void testIsOnDiagonalIsTrue() {
        this.coordinate.isOnDiagonal(this.onNEDiagonalCoordinate);
    }

    @Test
    public void testIsOnDiagonalIsFalse() {
        this.coordinate.isOnDiagonal(new Coordinate(this.row + 1, this.column));
    }

    @Test(expected = AssertionError.class)
    public void testIsOnDiagonalWithNullInput() {
        this.coordinate.isOnDiagonal(null);
    }

    @Test
    public void testGetDiagonalDistance() {
        Assert.assertEquals(2, this.coordinate.getDiagonalDistance(this.onNEDiagonalCoordinate));
        Assert.assertEquals(1, this.coordinate.getDiagonalDistance(new Coordinate(this.row + 1, this.column + 1)));
    }

    @Test(expected = AssertionError.class)
    public void testGetDiagonalDistanceNoDistance() {
        Assert.assertEquals(0, this.coordinate.getDiagonalDistance(this.coordinate));
    }

    @Test(expected = AssertionError.class)
    public void getBetweenDiagonalCoordinatesIsNotOnDiagonal() {
        Coordinate coordinate = Direction.NE.getDistanceCoordinate(3);
        this.coordinate.getBetweenDiagonalCoordinates(coordinate);
    }

    @Test
    public void getBetweenCoordinates() {
        Coordinate coordinate = Direction.NE.getDistanceCoordinate(3);
        Coordinate coordinate2 = Direction.NE.getDistanceCoordinate(1);
        Coordinate coordinateBetween = new Coordinate(2, 2);
        Assert.assertEquals(Arrays.asList(coordinateBetween), coordinate.getBetweenDiagonalCoordinates(coordinate2));
    }
}