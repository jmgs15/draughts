package usantatecla.draughts.views;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import usantatecla.draughts.controllers.StartController;
import usantatecla.draughts.utils.Console;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class StartViewTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @InjectMocks
    private View view;

    @Spy
    private StartController startController;

    @Spy
    private Console console;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        System.setOut(new PrintStream(outContent));
    }

    @Test(expected = AssertionError.class)
    public void testInteractNullControllerShouldThrowError() {
        view.interact(null);
    }

    @Test
    public void testInteractShouldBeCalledOnce() {
        this.view.interact(this.startController);
        verify(startController, times(1)).start();
        verify(startController, atMost(1)).start();
    }

    @Test
    public void testInteractConsoleShouldWriteTitle() {
        this.view.interact(this.startController);
        // 1 vez del título y 8 de las filas que contienen fichas del tablero
        verify(this.console, times(9)).writeln(anyString());
        // 2 veces de la filas que contienen los números de las columnas
        verify(this.console, times(2)).writeln();
    }

    @Test
    public void testInteractConsoleShouldPrintTitle() {
        view.interact(this.startController);
        Assert.assertTrue(outContent.toString().contains("Draughts"));
    }
}