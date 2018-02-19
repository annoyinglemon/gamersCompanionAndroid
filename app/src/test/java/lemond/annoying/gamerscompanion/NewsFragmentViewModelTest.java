package lemond.annoying.gamerscompanion;

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
import lemond.annoying.gamerscompanion.fragment_news.model.NewsRepository;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsItemViewModel;
import lemond.annoying.gamerscompanion.core.exception.NoConnectivityException;
import lemond.annoying.gamerscompanion.repository.service.DataWrapper;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class NewsFragmentViewModelTest {

    private NewsFragmentViewModel newsFragmentViewModel;

    @Rule
    public RxSchedulerRule rxSchedulerRule = new RxSchedulerRule();
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
    public void testInitialFetchingOfData() {
        Single<List<NewsItemViewModel>> newsListSingleObservable = Single.just(newsItemViewModels);
        when(newsRepository.fetchData()).thenReturn(newsListSingleObservable);
        newsFragmentViewModel.fetchData(false);

        verify(newsRepository).fetchData();
        assertNotNull(newsFragmentViewModel.getLiveData().getValue());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().state, DataWrapper.State.CONTENT);
        assertEquals(newsFragmentViewModel.getLiveData().getValue().size, newsItemViewModels.size());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().data, newsItemViewModels);
    }

    @Test
    public void testInitialFetchingOfData_JustCalledOnce() {
        Single<List<NewsItemViewModel>> newsListSingleObservable = Single.just(newsItemViewModels);
        when(newsRepository.fetchData()).thenReturn(newsListSingleObservable);
        newsFragmentViewModel.fetchData(false);
        newsFragmentViewModel.fetchData(false);

        verify(newsRepository, times(1)).fetchData();
        assertNotNull(newsFragmentViewModel.getLiveData().getValue());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().state, DataWrapper.State.CONTENT);
        assertEquals(newsFragmentViewModel.getLiveData().getValue().size, newsItemViewModels.size());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().data, newsItemViewModels);
    }

    @Test
    public void testFetchingOfData_ByForce() {
        Single<List<NewsItemViewModel>> newsListSingleObservable = Single.just(newsItemViewModels);
        when(newsRepository.fetchData()).thenReturn(newsListSingleObservable);
        newsFragmentViewModel.fetchData(false);
        newsFragmentViewModel.fetchData(false);
        newsFragmentViewModel.fetchData(false);
        newsFragmentViewModel.fetchData(true);

        verify(newsRepository, times(2)).fetchData();
        assertNotNull(newsFragmentViewModel.getLiveData().getValue());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().state, DataWrapper.State.CONTENT);
        assertEquals(newsFragmentViewModel.getLiveData().getValue().size, newsItemViewModels.size());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().data, newsItemViewModels);
    }

    @Test
    public void testFetchingOfData_Failed() {
        Single<List<NewsItemViewModel>> newsListSingleObservable = Single.error(new Exception());
        when(newsRepository.fetchData()).thenReturn(newsListSingleObservable);
        newsFragmentViewModel.fetchData(false);

        verify(newsRepository).fetchData();
        assertNotNull(newsFragmentViewModel.getLiveData().getValue());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().state, DataWrapper.State.ERROR);
        assertEquals(newsFragmentViewModel.getLiveData().getValue().size, 1);
        assertEquals(newsFragmentViewModel.getLiveData().getValue().data, null);
    }

    @Test
    public void testFetchingOfData_FailedBecauseOfNoNetwork() {
        Single<List<NewsItemViewModel>> newsListSingleObservable = Single.error(new NoConnectivityException());
        when(newsRepository.fetchData()).thenReturn(newsListSingleObservable);
        newsFragmentViewModel.fetchData(false);

        verify(newsRepository).fetchData();
        assertNotNull(newsFragmentViewModel.getLiveData().getValue());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().state, DataWrapper.State.NO_INTERNET);
        assertEquals(newsFragmentViewModel.getLiveData().getValue().size, 1);
        assertEquals(newsFragmentViewModel.getLiveData().getValue().data, null);
    }

    @Test
    public void testFetchingOfData_Success() {
        Single<List<NewsItemViewModel>> newsListSingleObservable = Single.just(newsItemViewModels);
        when(newsRepository.fetchData()).thenReturn(newsListSingleObservable);
        newsFragmentViewModel.fetchData(false);

        verify(newsRepository).fetchData();
        assertNotNull(newsFragmentViewModel.getLiveData().getValue());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().state, DataWrapper.State.CONTENT);
        assertEquals(newsFragmentViewModel.getLiveData().getValue().size, newsItemViewModels.size());
        assertEquals(newsFragmentViewModel.getLiveData().getValue().data, newsItemViewModels);
    }

}
