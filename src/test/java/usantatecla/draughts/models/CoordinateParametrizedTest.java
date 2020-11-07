package java.usantatecla.draughts.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import usantatecla.draughts.models.Coordinate;
import org.junit.runners.Parameterized.Parameters;
import usantatecla.draughts.models.Direction;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CoordinateParametrizedTest {
    private int OUTRow;
    private int OUTColumn;

    private Coordinate coordinate;
    private Direction expectedDirection;

    private String getInstanceOf;
    private Coordinate expectedCoordinateInGetInstance;
    private boolean example1;
    private boolean example2;

    @Parameters(name = "(Row: {0} Column: {1})")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1, 1, new Coordinate(3, 3), Direction.NE, "32", new Coordinate(2, 1), true, false},
                {1, 1, new Coordinate(1,2), null, "99", null, true, false},
                {1, 1, new Coordinate(1,1), null, "unparseable", null, true, false},
        });
    }

    public CoordinateParametrizedTest(int OUTRow, int OUTColumn, Coordinate coordinate, Direction expectedDirection, String getInstanceOf,
                                      Coordinate expectedCoordinateInGetInstance, boolean example1, boolean example2) {
        this.OUTRow = OUTRow;
        this.OUTColumn = OUTColumn;
        this.coordinate = coordinate;
        this.expectedDirection = expectedDirection;
        this.getInstanceOf = getInstanceOf;
        this.expectedCoordinateInGetInstance = expectedCoordinateInGetInstance;
        this.example1 = example1;
        this.example2 = example2;
    }

    private Coordinate OUTCoordinate;

    @Before
    public void beforeEach() {
        OUTCoordinate = new Coordinate(OUTRow, OUTColumn);
    }

    //No se como comprobar cuando getInstanceOf = null -> throws AssertionError
    @Test
    public void testGetInstance() {
        Assert.assertEquals(expectedCoordinateInGetInstance, Coordinate.getInstance(getInstanceOf));
    }

    @Test
    public void testGetDirection() {
        Assert.assertEquals(expectedDirection, this.OUTCoordinate.getDirection(coordinate));
    }

}
