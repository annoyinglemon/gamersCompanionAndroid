package lemond.annoying.gamerscompanion.fragment_news.model;


import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsItemViewModel;
import lemond.annoying.gamerscompanion.repository.model.DataRepository;
import lemond.annoying.gamerscompanion.repository.objects.Pulse;
import lemond.annoying.gamerscompanion.repository.service.NewsService;
import lemond.annoying.gamerscompanion.repository.util.PulseUtil;


public class NewsRepository implements DataRepository<List<NewsItemViewModel>> {

    private Resources resources;
    private NewsService newsService;

    @Inject
    NewsRepository(Resources resources, NewsService newsService) {
        this.resources = resources;
        this.newsService = newsService;
    }

    @Override
    public Single<List<NewsItemViewModel>> fetchData() {
        return newsService.getLatestNewsIds(System.currentTimeMillis()).flatMap(pulses -> {
            String commaSeparatedIds = PulseUtil.createGameIdsPath(pulses);
            return newsService.getNewsDetails(commaSeparatedIds);
        }).flatMap(pulseList -> {
            List<NewsItemViewModel> newsItemViewModels = new ArrayList<>();
            for (Pulse pulse : pulseList) {
                NewsItemViewModel newsItemViewModel = new NewsItemViewModel(resources, pulse);
                newsItemViewModels.add(newsItemViewModel);
            }
            return Single.just(newsItemViewModels);
        });
    }
}

