package lemond.annoying.gamerscompanion.repository.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lemond.annoying.gamerscompanion.repository.exception.NoConnectivityException;
import lemond.annoying.gamerscompanion.repository.model.DataRepository;
import lemond.annoying.gamerscompanion.repository.service.DataWrapper;


public class DataFetcherViewModel<T> extends ViewModel {

    private MutableLiveData<DataWrapper<T>> data;
    private DataRepository<T> dataRepository;
    private boolean isDataInitialized;
    private Disposable fetchSubscription;

    public DataFetcherViewModel(DataRepository<T> dataRepository) {
        this.dataRepository = dataRepository;
        this.data = new MutableLiveData<>();
    }

    public LiveData<DataWrapper<T>> getData() {
        return this.data;
    }

    /**
     * Begin fetching of data from server.
     * New data will not be fetched if this class already contains data unless forcibly set.
     *
     * @param byForce fetches data forcibly, false otherwise
     */
    public void fetchData(boolean byForce) {
        if (!isDataInitialized || byForce) {
            unSubscribe();
            fetchSubscription = dataRepository.fetchData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onFetchSuccess, this::onFetchFailed);
        }
    }

    private void onFetchSuccess(T dataResult) {
        DataWrapper<T> dataWrapper = new DataWrapper<>();
        dataWrapper.data = dataResult;
        dataWrapper.size = 1;

        if (dataResult != null) {
            if (dataResult instanceof List) {
                if (!((List) dataResult).isEmpty()) {
                    dataWrapper.state = DataWrapper.State.CONTENT;
                    dataWrapper.size = ((List) dataResult).size();
                } else {
                    dataWrapper.state = DataWrapper.State.EMPTY;
                }
            } else {
                dataWrapper.state = DataWrapper.State.CONTENT;
            }
        } else {
            dataWrapper.state = DataWrapper.State.EMPTY;
        }

        data.setValue(dataWrapper);
        isDataInitialized = true;
    }

    private void onFetchFailed(Throwable throwable) {
        DataWrapper<T> dataWrapper = new DataWrapper<>();
        if (throwable instanceof NoConnectivityException) {
            dataWrapper.state = DataWrapper.State.NO_INTERNET;
        } else {
            dataWrapper.state = DataWrapper.State.ERROR;
        }
        dataWrapper.size = 1;
        data.setValue(dataWrapper);
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
