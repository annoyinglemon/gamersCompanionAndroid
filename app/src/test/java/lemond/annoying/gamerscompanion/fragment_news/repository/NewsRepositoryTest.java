package lemond.annoying.gamerscompanion.fragment_news.repository;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Single;
import lemond.annoying.gamerscompanion.fragment_news.model.NewsRepository;
import lemond.annoying.gamerscompanion.repository.objects.Pulse;
import lemond.annoying.gamerscompanion.repository.service.NewsService;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NewsRepositoryTest {

    private NewsRepository newsRepository;

    @Mock
    private NewsService newsService;

    @Mock
    private List<Pulse> pulseList;

    @Before
    public void setUp() {
        newsRepository = new NewsRepository(newsService);
    }

    @Test
    public void testFetchingOfNews() {

        when(newsService.getLatestNews(System.currentTimeMillis())).thenReturn(Single.just(pulseList));

        newsRepository.fetchData();

        Mockito.verify(newsService).getLatestNews(System.currentTimeMillis());
    }

}
