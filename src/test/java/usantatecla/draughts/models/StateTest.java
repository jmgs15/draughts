package usantatecla.draughts.models;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.JVM)
public class StateTest {

    private static State state;

    @BeforeClass
    public static void beforeClass() {
        state = new State();
    }

    @Test
    public void testStateIsInitialStateWhenCreated() {
        Assert.assertEquals(getStateValue(0), state.getValueState());
    }

    @Test
    public void testNext() {
        state.next();
        Assert.assertEquals(getStateValue(1), state.getValueState());
        state.next();
        Assert.assertEquals(getStateValue(2), state.getValueState());
        state.next();
        Assert.assertEquals(getStateValue(3), state.getValueState());
    }

    @Test(expected = AssertionError.class)
    public void testNextWhenStateValueIsExitShouldThrowsError() {
        state.next();
    }

    @Test
    public void testReset() {
        state.reset();
        Assert.assertEquals(getStateValue(0),state.getValueState());
    }

    private StateValue getStateValue(int position) {
        StateValue[] values = StateValue.values();
        return values[position];
    }
}