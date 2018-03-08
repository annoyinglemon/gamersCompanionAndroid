package lemond.annoying.gamerscompanion.main_activity;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import lemond.annoying.gamerscompanion.activity.viewmodel.MainActivityViewModel;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityViewModelTest {

    private MainActivityViewModel mainActivityViewModel;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private Observer<Integer> pageChangeObserver;

    @Before
    public void start() {
        mainActivityViewModel = new MainActivityViewModel();
        mainActivityViewModel.getSelectedPageLiveData().observeForever(pageChangeObserver);
    }

    @After
    public void finish() {
        mainActivityViewModel.getSelectedPageLiveData().removeObserver(pageChangeObserver);
    }

    @Test
    public void testSetSelectedPage() {
        int selectedPage = 2;
        mainActivityViewModel.setSelectedPage(selectedPage);

        assertNotNull(mainActivityViewModel.getSelectedPageLiveData().getValue());
        verify(pageChangeObserver).onChanged(2);
        assertTrue(mainActivityViewModel.getSelectedPageLiveData().getValue() == 2);
    }

    @Test
    public void testCurrentPageShouldScrollToTop_withoutAnimation() {
        int selectedPage = 2;
        mainActivityViewModel.setSelectedPage(selectedPage);

        assertNotNull(mainActivityViewModel.getSelectedPageLiveData().getValue());
        assertTrue(mainActivityViewModel.getSelectedPageLiveData().getValue() == 2);
        verify(pageChangeObserver).onChanged(2);

        selectedPage = 0;
        mainActivityViewModel.setSelectedPage(selectedPage);
        assertNotNull(mainActivityViewModel.getSelectedPageLiveData().getValue());
        assertTrue(mainActivityViewModel.getSelectedPageLiveData().getValue() == 0);
        assertFalse(mainActivityViewModel.shouldAnimateScrollToTop());
        verify(pageChangeObserver, times(2)).onChanged(0);
    }

    @Test
    public void testCurrentPageShouldScrollToTop_withAnimation() {
        int selectedPage = 2;
        mainActivityViewModel.setSelectedPage(selectedPage);

        assertNotNull(mainActivityViewModel.getSelectedPageLiveData().getValue());
        assertTrue(mainActivityViewModel.getSelectedPageLiveData().getValue() == 2);
        verify(pageChangeObserver).onChanged(2);

        selectedPage = 2;
        mainActivityViewModel.setSelectedPage(selectedPage);
        assertNotNull(mainActivityViewModel.getSelectedPageLiveData().getValue());
        assertTrue(mainActivityViewModel.getSelectedPageLiveData().getValue() == 2);
        assertTrue(mainActivityViewModel.shouldAnimateScrollToTop());
        verify(pageChangeObserver, times(2)).onChanged(2);
    }

}
