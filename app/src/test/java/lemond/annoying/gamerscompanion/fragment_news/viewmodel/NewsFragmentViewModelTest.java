package lemond.annoying.gamerscompanion.fragment_news.viewmodel;

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
import lemond.annoying.gamerscompanion.main_activity.fragment_news.model.NewsRepository;
import lemond.annoying.gamerscompanion.core.exception.NoConnectivityException;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsFragmentViewModel;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsItemViewModel;
import lemond.annoying.gamerscompanion.repository.service.DataWrapper;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class NewsFragmentViewModelTest {

    private NewsFragmentViewModel newsFragmentViewModel;

    @Rule
    public RxSchedulerRule rxSchedulerRule = new RxSchedulerRule();
    //   https://medium.com/@veniosg/unit-testing-with-mutablelivedata-22b3283a7819
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private NewsRepository newsRepository;
    @Mock
    private List<NewsItemViewModel> newsItemViewModels;

    @Before
    public void setUp() {
        newsFragmentViewModel = new NewsFragmentViewModel(newsRepository);
    }

    @Test
    public void testShowLoadingState() {
        newsFragmentViewModel.showLoadingState();
        assertContentLoading();
    }

    @Test
    public void testInitialFetchingOfData() {
        createSuccessScenarioThenFetchData(false);

        verify(newsRepository).fetchData();
        assertContentResult();
    }

    @Test
    public void testFetchingOfData_JustCalledOnce() {
        createSuccessScenarioThenFetchData(false);

        newsFragmentViewModel.fetchData(false);

        verify(newsRepository, times(1)).fetchData();
        assertContentResult();
    }

    @Test
    public void testFetchingOfData_ByForce() {
        createSuccessScenarioThenFetchData(false);

        newsFragmentViewModel.fetchData(false);
        newsFragmentViewModel.fetchData(false);
        newsFragmentViewModel.fetchData(true);

        verify(newsRepository, times(2)).fetchData();
        assertContentResult();
    }

    @Test
    public void testFetchingOfData_Failed() {
        createErrorScenarioThenFetchData(false);

        verify(newsRepository).fetchData();
        assertErrorResult();
    }

    @Test
    public void testFetchingOfData_Failed_SuccessAfterRefresh() {
        createErrorScenarioThenFetchData(false);

        verify(newsRepository).fetchData();
        assertErrorResult();

        createSuccessScenarioThenFetchData(true);

        verify(newsRepository, times(2)).fetchData();
        assertContentResult();
    }

    @Test
    public void testFetchingOfData_FailedBecauseOfNoNetwork() {
        createNoInternetScenarioThenFetchData(false);

        verify(newsRepository).fetchData();
        assertNetworkErrorResult();
    }

    @Test
    public void testFetchingOfData_FailedBecauseOfNoNetwork_SuccessAfterRefresh() {
        createNoInternetScenarioThenFetchData(false);

        verify(newsRepository).fetchData();
        assertNetworkErrorResult();

        createSuccessScenarioThenFetchData(true);

        verify(newsRepository, times(2)).fetchData();
        assertContentResult();
    }

    private void createSuccessScenarioThenFetchData(boolean byForce) {
        Single<List<NewsItemViewModel>> newsListSingleObservable = Single.just(newsItemViewModels);
        when(newsRepository.fetchData()).thenReturn(newsListSingleObservable);
        newsFragmentViewModel.fetchData(byForce);
    }

    private void createErrorScenarioThenFetchData(boolean byForce) {
        Single<List<NewsItemViewModel>> errorObservable = Single.error(new Exception());
        when(newsRepository.fetchData()).thenReturn(errorObservable);
        newsFragmentViewModel.fetchData(byForce);
    }

    private void createNoInternetScenarioThenFetchData(boolean byForce) {
        Single<List<NewsItemViewModel>> noConnectivityObservable = Single.error(new NoConnectivityException());
        when(newsRepository.fetchData()).thenReturn(noConnectivityObservable);
        newsFragmentViewModel.fetchData(byForce);
    }

    private void assertContentLoading() {
        assertNotNull(newsFragmentViewModel.getLiveData().getValue());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().state, DataWrapper.State.LOADING);
        assertEquals(newsFragmentViewModel.getLiveData().getValue().data, null);
    }

    private void assertContentResult() {
        assertNotNull(newsFragmentViewModel.getLiveData().getValue());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().state, DataWrapper.State.CONTENT);
        assertEquals(newsFragmentViewModel.getLiveData().getValue().data, newsItemViewModels);
    }

    private void assertErrorResult() {
        assertNotNull(newsFragmentViewModel.getLiveData().getValue());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().state, DataWrapper.State.ERROR);
        assertEquals(newsFragmentViewModel.getLiveData().getValue().data, null);
    }

    private void assertNetworkErrorResult() {
        assertNotNull(newsFragmentViewModel.getLiveData().getValue());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().state, DataWrapper.State.NO_INTERNET);
        assertEquals(newsFragmentViewModel.getLiveData().getValue().data, null);
    }

}
