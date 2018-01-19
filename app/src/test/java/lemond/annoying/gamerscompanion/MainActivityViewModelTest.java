package lemond.annoying.gamerscompanion;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import lemond.annoying.gamerscompanion.activity.viewmodel.MainActivityViewModel;

public class MainActivityViewModelTest {

    @Mock
    private MainActivityViewModel mainActivityViewModel;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testSetSelectedPage() {
        int selectedPage = 2;
        mainActivityViewModel.setSelectedPage(selectedPage);
        when(mainActivityViewModel.getSelectedPage()).thenReturn(selectedPage);
        assertTrue(mainActivityViewModel.getSelectedPage() == 2);
    }

}
