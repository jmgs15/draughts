package usantatecla.draughts.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;

public class YesNoDialogTest {

    @Mock
    Console console;

    @InjectMocks
    private YesNoDialog yesNoDialog = new YesNoDialog();

    private char YES = 'y';
    private char NO = 'n';

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        this.yesNoDialog = new YesNoDialog();
    }

    @Test(expected = AssertionError.class)
    public void testReadWhenTitleIsNull() {
        this.yesNoDialog.read(null);
    }

    @Test
    public void testAnswerYes() {
        Mockito.when(this.console.readChar(any())).thenReturn(this.YES);
//        Assert.assertTrue(this.yesNoDialog.read("title"));
    }
}