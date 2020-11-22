package usantatecla.draughts.views;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import usantatecla.draughts.controllers.PlayController;
import usantatecla.draughts.models.Color;
import usantatecla.draughts.models.Error;
import usantatecla.draughts.utils.Console;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class PlayViewTest {

    private static final String CANCEL_FORMAT = "-1";
    private static final String LOST_MESSAGE = "Derrota!!! No puedes mover tus fichas!!!";

    @InjectMocks
    View view;

    @Mock
    Console console;

    @Spy
    PlayController playController;

    @Before
    public void before() {
        initMocks(this);
        doReturn(Color.WHITE).when(playController).getColor();
        doReturn(Error.NULL).when(playController).move(any());
    }

    @Test
    public void testWhenIntroducingCancelFormatThenCancelInvoked() {
        //TODO: Preguntar por qué con el @Spy de console no funciona el when().thenReturn()
        doReturn(CANCEL_FORMAT).when(console).readString(any());
        //when(console.readString(anyString())).thenReturn(CANCEL_FORMAT);
        view.interact(playController);
        verify(playController, times(1)).cancel();
    }

    @Test
    public void testWhenIntroducingWrongFormatCoordinateThenAskAgain() {
        doReturn("xxx", "12.23").when(console).readString(any());
        view.interact(playController);
        verify(console, times(2)).readString(any());
    }

    @Test
    public void testWhenIntroducingValidCoordinateAndBlockedThenLoose() {
        doReturn("12.23").when(console).readString(any());
        doReturn(true).when(playController).isBlocked();
        view.interact(playController);
        verify(console, times(1)).writeln(LOST_MESSAGE);
    }
}
