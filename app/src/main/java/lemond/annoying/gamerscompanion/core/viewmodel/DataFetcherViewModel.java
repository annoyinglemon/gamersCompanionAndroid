package lemond.annoying.gamerscompanion.core.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lemond.annoying.gamerscompanion.core.exception.NoConnectivityException;
import lemond.annoying.gamerscompanion.core.model.DataRepository;
import lemond.annoying.gamerscompanion.repository.service.DataWrapper;


public class DataFetcherViewModel<T> extends ViewModel {

    private MutableLiveData<DataWrapper<T>> liveData;
    protected DataRepository<T> dataRepository;
    private boolean isDataInitialized;
    private Disposable fetchSubscription;

    public DataFetcherViewModel(DataRepository<T> dataRepository) {
        this.dataRepository = dataRepository;
        this.liveData = new MutableLiveData<>();
    }

    public LiveData<DataWrapper<T>> getLiveData() {
        return this.liveData;
    }

    /**
     * Begin fetching of liveData from server.
     * New liveData will not be fetched if this class already contains liveData unless forcibly set.
     *
     * @param byForce fetches liveData forcibly, false otherwise
     */
    public void fetchData(boolean byForce) {

        if (!isDataInitialized || byForce) {
            unSubscribe();

            if (!byForce) {
                DataWrapper<T> dataWrapper = new DataWrapper<>();
                dataWrapper.state = DataWrapper.State.LOADING;
                liveData.setValue(dataWrapper);
            }

            fetchSubscription = dataRepository.fetchData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onFetchSuccess, this::onFetchFailed);
        }
    }

    private void onFetchSuccess(T dataResult) {
        DataWrapper<T> dataWrapper = new DataWrapper<>();
        dataWrapper.data = dataResult;

        if (dataResult != null) {
            if (dataResult instanceof List) {
                if (!((List) dataResult).isEmpty()) {
                    dataWrapper.state = DataWrapper.State.CONTENT;
                } else {
                    dataWrapper.state = DataWrapper.State.EMPTY;
                }
            } else {
                dataWrapper.state = DataWrapper.State.CONTENT;
            }
        } else {
            dataWrapper.state = DataWrapper.State.EMPTY;
        }

        liveData.setValue(dataWrapper);
        isDataInitialized = true;
    }

    private void onFetchFailed(Throwable throwable) {
        DataWrapper<T> dataWrapper = new DataWrapper<>();
        if (throwable instanceof NoConnectivityException) {
            dataWrapper.state = DataWrapper.State.NO_INTERNET;
        } else {
            dataWrapper.state = DataWrapper.State.ERROR;
        }
        liveData.setValue(dataWrapper);
    }

    @Override
    protected void onCleared() {
        unSubscribe();
        super.onCleared();
    }

    private void unSubscribe() {
        if (fetchSubscription != null && !fetchSubscription.isDisposed()) {
            fetchSubscription.dispose();
        }
    }
}
