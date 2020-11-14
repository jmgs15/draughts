package usantatecla.draughts.views;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import usantatecla.draughts.controllers.InteractorController;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class GameViewTest {
    private GameView gameView;
    private final int DIMENSION = 5;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Mock
    InteractorController interactorController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        System.setOut(new PrintStream(outContent));
        this.gameView = new GameView();
    }

    @Test
    public void testWriteNumberOfColumns() {
        when(this.interactorController.getDimension()).thenReturn(DIMENSION);
        this.gameView.write(interactorController);
        assertTrue(this.outContent.toString().contains("12345"));
    }

    @Test
    public void testWriteFirstRowNumberAtTheBeginning() {
        when(this.interactorController.getDimension()).thenReturn(DIMENSION);
        this.gameView.write(interactorController);
        assertTrue(outputConsoleLines().get(0).contains("1"));
    }

    @Test
    public void testWriteLastRowNumberAtTheBeginning() {
        when(this.interactorController.getDimension()).thenReturn(DIMENSION);
        this.gameView.write(interactorController);
        assertTrue(outputConsoleLines().get(0).contains("5"));
    }

    @Test
    public void testWriteFirstRowNumberAtLastOfLine() {
        when(this.interactorController.getDimension()).thenReturn(DIMENSION);
        this.gameView.write(interactorController);
        assertEquals("1", lastCharacterOfLine(outputConsoleLines().get(0)));
    }

    @Test
    public void testWriteLastRowNumberAtLastOfLine() {
        when(this.interactorController.getDimension()).thenReturn(DIMENSION);
        this.gameView.write(interactorController);
        assertEquals("5", lastCharacterOfLine(outputConsoleLines().get(DIMENSION - 1)));
    }

    //TODO: test pieces.
    
    private List<String> outputConsoleLines() {
        String output = this.outContent.toString();
        List<String> lines = new ArrayList<>();
        lines.addAll(Arrays.asList(output.split("\n")));
        lines.remove(0);
        lines.remove(lines.size() - 1);
        return lines;
    }

    private String lastCharacterOfLine(String line) {
        List<String> characters = Arrays.asList(line.split(""));
        return characters.get(characters.size() - 1);
    }
}
