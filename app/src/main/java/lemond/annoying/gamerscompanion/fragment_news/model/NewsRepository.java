package lemond.annoying.gamerscompanion.fragment_news.model;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import lemond.annoying.gamerscompanion.app.ViewControllerScope;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsItemViewModel;
import lemond.annoying.gamerscompanion.core.model.DataRepository;
import lemond.annoying.gamerscompanion.repository.objects.Pulse;
import lemond.annoying.gamerscompanion.repository.service.NewsService;

@ViewControllerScope
public class NewsRepository implements DataRepository<List<NewsItemViewModel>> {

    private final NewsService newsService;

    @Inject
    public NewsRepository(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public Single<List<NewsItemViewModel>> fetchData() {
        return newsService.getLatestNews(System.currentTimeMillis()).flatMap(pulses -> {
            List<NewsItemViewModel> newsItemViewModels = new ArrayList<>();
            for (Pulse pulse : pulses) {
                NewsItemViewModel newsItemViewModel = new NewsItemViewModel(pulse);
                newsItemViewModels.add(newsItemViewModel);
            }
            return Single.just(newsItemViewModels);
        });
    }
}

