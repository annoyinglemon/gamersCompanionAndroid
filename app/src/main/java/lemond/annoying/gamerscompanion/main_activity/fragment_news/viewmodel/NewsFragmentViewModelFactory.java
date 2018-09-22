package lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.main_activity.fragment_news.injection.NewsFragmentScope;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.model.NewsRepository;

@NewsFragmentScope
public class NewsFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final NewsRepository newsRepository;

    @Inject
    NewsFragmentViewModelFactory(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewsFragmentViewModel.class)) {
            return (T) new NewsFragmentViewModel(newsRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}