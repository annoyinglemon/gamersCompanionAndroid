package lemond.annoying.gamerscompanion.fragment_news.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_news.model.NewsModel;
import lemond.annoying.gamerscompanion.repository.service.DataState;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<DataState<List<NewsItemViewModel>>> latestNews;
    private NewsModel newsModel;

    @Inject
    public NewsViewModel(NewsModel newsModel) {
        this.newsModel = newsModel;
    }

    public void initializeData() {
        newsModel.getLatestNews();
    }

    public LiveData<DataState<List<NewsItemViewModel>>> getLatestNews() {
        if (latestNews == null) {
            latestNews = new MutableLiveData<>();
        }
        return this.latestNews;
    }

}
