package usantatecla.draughts.models;

import org.junit.Assert;
import org.junit.Test;

public class ColorTest {
    Color white = Color.WHITE;
    Color black = Color.BLACK;

    @Test
    public void testIsInitialRow() {
        Assert.assertTrue(black.isInitialRow(0));
        Assert.assertTrue(black.isInitialRow(2));
        Assert.assertFalse(black.isInitialRow(3));

        Assert.assertTrue(white.isInitialRow(5));
        Assert.assertTrue(white.isInitialRow(8));
    }

    @Test
    public void testGetInitialColorWhenCoordinateIsOnInitialRowsShouldReturnColor() {
        Assert.assertEquals(black, Color.getInitialColor(new Coordinate(0, 1)));
        Assert.assertEquals(white, Color.getInitialColor(new Coordinate(5, 0)));
    }

    @Test
    public void testGetInitialColorWhenCoordinateIsWhiteShouldReturnNull() {
        Assert.assertNull(Color.getInitialColor(new Coordinate(0, 0)));
        Assert.assertNull(Color.getInitialColor(new Coordinate(2, 2)));
    }

    @Test
    public void testGetInitialColorWhenCoordinateDoesntHavePieceShouldReturnNull() {
        Assert.assertNull(Color.getInitialColor(new Coordinate(3, 3)));
        Assert.assertNull(Color.getInitialColor(new Coordinate(4, 4)));
    }
}