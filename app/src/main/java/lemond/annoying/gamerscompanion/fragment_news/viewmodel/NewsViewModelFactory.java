package lemond.annoying.gamerscompanion.fragment_news.viewmodel;


import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_news.model.NewsModel;

public class NewsViewModelFactory implements ViewModelProvider.Factory {

    private final NewsModel newsModel;

    @Inject
    public NewsViewModelFactory(NewsModel newsModel) {
        this.newsModel = newsModel;
    }

    @Override
    @NonNull
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewsViewModel.class)) {
            return (T) new NewsViewModel(newsModel);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
