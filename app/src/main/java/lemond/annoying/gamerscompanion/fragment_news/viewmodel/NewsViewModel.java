package lemond.annoying.gamerscompanion.fragment_news.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;


import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import lemond.annoying.gamerscompanion.fragment_news.model.NewsModel;
import lemond.annoying.gamerscompanion.repository.service.DataState;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<DataState<List<NewsItemViewModel>>> latestNews;
    private NewsModel newsModel;
    private SingleObserver<List<NewsItemViewModel>> newsItemViewModelObserver;
    private Disposable disposable;

    public NewsViewModel(NewsModel newsModel) {
        this.newsModel = newsModel;
        this.latestNews = new MutableLiveData<>();
        this.initializeObserver();
    }

    public void initializeData() {
        newsModel.getLatestNews().subscribe(newsItemViewModelObserver);
    }

    private void initializeObserver() {
        dispose();

        newsItemViewModelObserver = new SingleObserver<List<NewsItemViewModel>>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(List<NewsItemViewModel> newsItemViewModels) {
                DataState<List<NewsItemViewModel>> listDataState = new DataState<>();
                listDataState.data = newsItemViewModels;
                if (!newsItemViewModels.isEmpty()) {
                    listDataState.state = DataState.State.CONTENT;
                    listDataState.size = newsItemViewModels.size();
                } else {
                    listDataState.state = DataState.State.EMPTY;
                    listDataState.size = 1;
                }
                latestNews.setValue(listDataState);
            }

            @Override
            public void onError(Throwable e) {
                DataState<List<NewsItemViewModel>> listDataState = new DataState<>();
                listDataState.state = DataState.State.ERROR;
                listDataState.size = 1;
                latestNews.setValue(listDataState);
            }
        };
    }

    public LiveData<DataState<List<NewsItemViewModel>>> getLatestNews() {
        return this.latestNews;
    }

    @Override
    protected void onCleared() {
        dispose();
        super.onCleared();
    }

    private void dispose() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
