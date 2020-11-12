package usantatecla.draughts.views;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import usantatecla.draughts.controllers.ResumeController;
import usantatecla.draughts.models.Game;
import usantatecla.draughts.models.State;
import usantatecla.draughts.models.StateValue;
import usantatecla.draughts.utils.Console;
import usantatecla.draughts.utils.YesNoDialog;

@RunWith(MockitoJUnitRunner.class)
public class ResumeViewTest {

	private static final String AFIRMATIVE = "y";
	private static final String NEGATIVE = "n";
	
	@Mock
	Console console;
	
	@Mock
	YesNoDialog yesNoDialog;
	
	@InjectMocks
	ResumeView resumeView = new ResumeView();
	
	@Test
	public void testGivenResumeQuestionOnYesThenResetState() {
		Game game = new Game();
		State state = new State();
		avanzarHasta(state, StateValue.FINAL);
		ResumeController resumeController = new ResumeController(game, state);
		when(this.yesNoDialog.read(Mockito.anyString())).thenReturn(true);
		this.resumeView.interact(resumeController);
		assertEquals(StateValue.INITIAL, state.getValueState());
	}
	
	@Test
	public void testGivenResumeQuestionOnNoThenStateFinal() {
		Game game = new Game();
		State state = new State();
		avanzarHasta(state, StateValue.FINAL);
		ResumeController resumeController = new ResumeController(game, state);
		when(this.yesNoDialog.read(Mockito.anyString())).thenReturn(false);
		this.resumeView.interact(resumeController);
		assertEquals(StateValue.EXIT, state.getValueState());
	}
	
	private void avanzarHasta(State state, StateValue stateValue) {
		while(state.getValueState() != stateValue) {
			state.next();
		}
	}
}
