package lemond.annoying.gamerscompanion.fragment_news.injection;


import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Resources;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GlideApp;
import lemond.annoying.gamerscompanion.app.GlideRequests;
import lemond.annoying.gamerscompanion.fragment_news.model.NewsModel;
import lemond.annoying.gamerscompanion.fragment_news.view.NewsAdapter;
import lemond.annoying.gamerscompanion.fragment_news.view.NewsFragment;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsViewModel;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsViewModelFactory;
import lemond.annoying.gamerscompanion.repository.service.NewsService;

@Module
public class NewsModule {

    private final NewsFragment newsFragment;

    public NewsModule(NewsFragment newsFragment) {
        this.newsFragment = newsFragment;
    }

    @Provides
    @NewsFragmentScope
    public GlideRequests provideGlideRequests() {
        return GlideApp.with(newsFragment);
    }

    @Provides
    @NewsFragmentScope
    public NewsAdapter provideNewsAdapter(GlideRequests glideRequests) {
        return new NewsAdapter(glideRequests);
    }

    @Provides
    @NewsFragmentScope
    public NewsModel provideNewsModel(Resources resources, NewsService newsService) {
        return new NewsModel(resources, newsService);
    }

    @Provides
    @NewsFragmentScope
    public NewsViewModelFactory provideNewsViewModelFactory(NewsModel newsModel) {
        return new NewsViewModelFactory(newsModel);
    }

    @Provides
    @NewsFragmentScope
    public NewsViewModel provideNewsViewModel(NewsViewModelFactory newsViewModelFactory) {
        return ViewModelProviders.of(newsFragment, newsViewModelFactory).get(NewsViewModel.class);
    }

}
