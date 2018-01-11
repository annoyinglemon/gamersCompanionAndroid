package lemond.annoying.gamerscompanion.fragment_news.injection;


import android.arch.lifecycle.ViewModelProviders;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GlideApp;
import lemond.annoying.gamerscompanion.app.GlideRequests;
import lemond.annoying.gamerscompanion.fragment_news.view.NewsFragment;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsFragmentViewModelFactory;

@Module
public class NewsModule {

    private final NewsFragment newsFragment;

    public NewsModule(NewsFragment newsFragment) {
        this.newsFragment = newsFragment;
    }

    @Provides
    @NewsFragmentScope
    GlideRequests provideGlideRequests() {
        return GlideApp.with(newsFragment);
    }

    @Provides
    @NewsFragmentScope
    NewsFragmentViewModel provideNewsFragmentViewModel(NewsFragmentViewModelFactory newsFragmentViewModelFactory) {
        return ViewModelProviders.of(newsFragment, newsFragmentViewModelFactory).get(NewsFragmentViewModel.class);
    }

}
