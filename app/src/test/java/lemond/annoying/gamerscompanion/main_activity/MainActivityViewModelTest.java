package lemond.annoying.gamerscompanion.main_activity;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import static org.junit.Assert.*;

import lemond.annoying.gamerscompanion.activity.viewmodel.MainActivityViewModel;

public class MainActivityViewModelTest {

    private MainActivityViewModel mainActivityViewModel;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        mainActivityViewModel = new MainActivityViewModel();
    }

    @Test
    public void testSetSelectedPage() {
        int selectedPage = 2;
        mainActivityViewModel.setSelectedPage(selectedPage);

        assertNotNull(mainActivityViewModel.getSelectedPageLiveData().getValue());
        assertTrue(mainActivityViewModel.getSelectedPageLiveData().getValue() == 2);
    }

    @Test
    public void testCurrentPageShouldScrollToTop_withoutAnimation() {
        int selectedPage = 2;
        mainActivityViewModel.setSelectedPage(selectedPage);

        assertNotNull(mainActivityViewModel.getSelectedPageLiveData().getValue());
        assertTrue(mainActivityViewModel.getSelectedPageLiveData().getValue() == 2);

        selectedPage = 0;
        mainActivityViewModel.setSelectedPage(selectedPage);
        assertNotNull(mainActivityViewModel.getSelectedPageLiveData().getValue());
        assertTrue(mainActivityViewModel.getSelectedPageLiveData().getValue() == 0);
        assertFalse(mainActivityViewModel.shouldAnimateScrollToTop());
    }

    @Test
    public void testCurrentPageShouldScrollToTop_withAnimation() {
        int selectedPage = 2;
        mainActivityViewModel.setSelectedPage(selectedPage);

        assertNotNull(mainActivityViewModel.getSelectedPageLiveData().getValue());
        assertTrue(mainActivityViewModel.getSelectedPageLiveData().getValue() == 2);

        selectedPage = 2;
        mainActivityViewModel.setSelectedPage(selectedPage);
        assertNotNull(mainActivityViewModel.getSelectedPageLiveData().getValue());
        assertTrue(mainActivityViewModel.getSelectedPageLiveData().getValue() == 2);
        assertTrue(mainActivityViewModel.shouldAnimateScrollToTop());
    }

}
