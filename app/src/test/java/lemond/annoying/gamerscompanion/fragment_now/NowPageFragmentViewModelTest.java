package lemond.annoying.gamerscompanion.fragment_now;


import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Single;
import lemond.annoying.gamerscompanion.RxSchedulerRule;
import lemond.annoying.gamerscompanion.core.exception.NoConnectivityException;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.viewmodel.GameItemViewModel;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.model.NowPageRepository;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageFragmentViewModel;
import lemond.annoying.gamerscompanion.repository.service.DataWrapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NowPageFragmentViewModelTest {

    private NowPageFragmentViewModel nowPageFragmentViewModel;

    @Rule
    public RxSchedulerRule rxSchedulerRule = new RxSchedulerRule();
    //   https://medium.com/@veniosg/unit-testing-with-mutablelivedata-22b3283a7819
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private NowPageRepository nowPageRepository;
    @Mock
    private List<GameItemViewModel> gameItemViewModels;

    @Before
    public void setUp() {
        nowPageFragmentViewModel = new NowPageFragmentViewModel(nowPageRepository);
    }

    @Test
    public void testShowLoadingState() {
        nowPageFragmentViewModel.showLoadingState();
        assertContentLoading();
    }

    @Test
    public void testInitialFetchingOfData() {
        createSuccessScenarioThenFetchData(false);

        verify(nowPageRepository).fetchData();
        assertContentResult();
    }

    @Test
    public void testFetchingOfData_JustCalledOnce() {
        createSuccessScenarioThenFetchData(false);

        nowPageFragmentViewModel.fetchData(false);

        verify(nowPageRepository, times(1)).fetchData();
        assertContentResult();
    }

    @Test
    public void testFetchingOfData_ByForce() {
        createSuccessScenarioThenFetchData(false);
        nowPageFragmentViewModel.fetchData(false);
        nowPageFragmentViewModel.fetchData(false);
        nowPageFragmentViewModel.fetchData(true);

        verify(nowPageRepository, times(2)).fetchData();
        assertContentResult();
    }

    @Test
    public void testFetchingOfDataFailed() {
        createErrorScenarioThenFetchData(false);

        verify(nowPageRepository).fetchData();
        assertErrorResult();
    }

    @Test
    public void testFetchingOfDataFailed_SuccessAfterRefresh() {
        createErrorScenarioThenFetchData(false);

        verify(nowPageRepository).fetchData();
        assertErrorResult();

        createSuccessScenarioThenFetchData(true);

        verify(nowPageRepository, times(2)).fetchData();
        assertContentResult();
    }

    @Test
    public void testFetchingOfData_FailedBecauseOfNoNetwork() {
        createNoInternetScenarioThenFetchData(false);

        verify(nowPageRepository).fetchData();
        assertNetworkErrorResult();
    }

    @Test
    public void testFetchingOfData_FailedBecauseOfNoNetwork_SuccessAfterRefresh() {
        createNoInternetScenarioThenFetchData(false);

        verify(nowPageRepository).fetchData();
        assertNetworkErrorResult();

        createSuccessScenarioThenFetchData(true);

        verify(nowPageRepository, times(2)).fetchData();
        assertContentResult();
    }

    private void createSuccessScenarioThenFetchData(boolean byForce) {
        Single<List<GameItemViewModel>> gameSingleObservable = Single.just(gameItemViewModels);
        when(nowPageRepository.fetchData()).thenReturn(gameSingleObservable);
        nowPageFragmentViewModel.fetchData(byForce);
    }

    private void createErrorScenarioThenFetchData(boolean byForce) {
        Single<List<GameItemViewModel>> errorObservable = Single.error(new Exception());
        when(nowPageRepository.fetchData()).thenReturn(errorObservable);
        nowPageFragmentViewModel.fetchData(byForce);
    }

    private void createNoInternetScenarioThenFetchData(boolean byForce) {
        Single<List<GameItemViewModel>> noConnectivityObservable = Single.error(new NoConnectivityException());
        when(nowPageRepository.fetchData()).thenReturn(noConnectivityObservable);
        nowPageFragmentViewModel.fetchData(byForce);
    }

    private void assertContentLoading() {
        assertNotNull(nowPageFragmentViewModel.getLiveData().getValue());
        assertEquals(nowPageFragmentViewModel.getLiveData().getValue().getState(), DataWrapper.State.LOADING);
        assertEquals(nowPageFragmentViewModel.getLiveData().getValue().getData(), null);
    }

    private void assertContentResult() {
        assertNotNull(nowPageFragmentViewModel.getLiveData().getValue());
        assertEquals(nowPageFragmentViewModel.getLiveData().getValue().getState(), DataWrapper.State.CONTENT);
        assertEquals(nowPageFragmentViewModel.getLiveData().getValue().getData(), gameItemViewModels);
    }

    private void assertErrorResult() {
        assertNotNull(nowPageFragmentViewModel.getLiveData().getValue());
        assertEquals(nowPageFragmentViewModel.getLiveData().getValue().getState(), DataWrapper.State.ERROR);
        assertEquals(nowPageFragmentViewModel.getLiveData().getValue().getData(), null);
    }

    private void assertNetworkErrorResult() {
        assertNotNull(nowPageFragmentViewModel.getLiveData().getValue());
        assertEquals(nowPageFragmentViewModel.getLiveData().getValue().getState(), DataWrapper.State.NO_INTERNET);
        assertEquals(nowPageFragmentViewModel.getLiveData().getValue().getData(), null);
    }

}
