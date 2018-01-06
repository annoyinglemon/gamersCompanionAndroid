package lemond.annoying.gamerscompanion.fragment_news.model;


import android.arch.lifecycle.MutableLiveData;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsItemViewModel;
import lemond.annoying.gamerscompanion.repository.service.DataState;
import lemond.annoying.gamerscompanion.repository.service.NewsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsModel {

    private Resources resources;
    private NewsService newsService;

    @Inject
    public NewsModel(Resources resources, NewsService newsService) {
        // TODO: 2018-01-05 use this resources to create pulse item viewmodel
        this.resources = resources;
        this.newsService = newsService;
    }

    public MutableLiveData<DataState<List<NewsItemViewModel>>> getLatestNews() {
        newsService.getLatestNewsIds(System.currentTimeMillis()).enqueue(new Callback<List<Long>>() {

            @Override
            @SuppressWarnings("ConstantConditions")
            public void onResponse(@NonNull Call<List<Long>> call, @NonNull Response<List<Long>> response) {

            }

            @Override
            public void onFailure(@NonNull Call<List<Long>> call, @NonNull Throwable t) {

            }
        });

        return new MutableLiveData<>();
    }
}
