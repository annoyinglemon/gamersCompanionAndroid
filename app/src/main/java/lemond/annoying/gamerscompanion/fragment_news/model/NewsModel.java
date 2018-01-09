package lemond.annoying.gamerscompanion.fragment_news.model;


import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import lemond.annoying.gamerscompanion.fragment_news.injection.NewsFragmentScope;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsItemViewModel;
import lemond.annoying.gamerscompanion.repository.objects.Pulse;
import lemond.annoying.gamerscompanion.repository.service.NewsService;
import lemond.annoying.gamerscompanion.repository.util.PulseUtil;

@NewsFragmentScope
public class NewsModel {

    private Resources resources;
    private NewsService newsService;

    @Inject
    public NewsModel(Resources resources, NewsService newsService) {
        this.resources = resources;
        this.newsService = newsService;
    }

    public Single<List<NewsItemViewModel>> getLatestNews() {
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
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

