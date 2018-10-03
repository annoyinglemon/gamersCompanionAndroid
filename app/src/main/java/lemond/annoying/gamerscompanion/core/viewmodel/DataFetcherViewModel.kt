package lemond.annoying.gamerscompanion.core.viewmodel


import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.support.annotation.VisibleForTesting
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import lemond.annoying.gamerscompanion.core.exception.NoConnectivityException
import lemond.annoying.gamerscompanion.core.model.DataRepository
import lemond.annoying.gamerscompanion.repository.service.DataWrapper


open class DataFetcherViewModel<T>(private val dataRepository: DataRepository<T>) : ViewModel() {

    internal val liveData: MutableLiveData<DataWrapper<T>> = MutableLiveData()
    private var isDataInitialized: Boolean = false
    private var fetchSubscription: Disposable? = null

    fun getLiveData(): LiveData<DataWrapper<T>> {
        return this.liveData
    }

    /**
     * Begin fetching of liveData from server.
     * New liveData will not be fetched if this class already contains liveData unless forcibly set.
     *
     * @param byForce fetches liveData forcibly, false otherwise
     */
    fun fetchData(byForce: Boolean) {

        if (!isDataInitialized || byForce) {
            unSubscribe()

            if (!byForce) {
                showLoadingState()
            }

            fetchSubscription = dataRepository.fetchData()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ this.onFetchSuccess(it) }, { this.onFetchFailed(it) })
        }
    }

    @VisibleForTesting
    fun showLoadingState() {
        val dataWrapper = DataWrapper<T>()
        dataWrapper.state = DataWrapper.State.LOADING
        liveData.value = dataWrapper
    }

    private fun onFetchSuccess(dataResult: T?) {
        val dataWrapper = DataWrapper<T>()
        dataWrapper.data = dataResult

        if (dataResult != null) {
            if (dataResult is List<*>) {
                if (!(dataResult as List<*>).isEmpty()) {
                    dataWrapper.state = DataWrapper.State.CONTENT
                } else {
                    dataWrapper.state = DataWrapper.State.EMPTY
                }
            } else {
                dataWrapper.state = DataWrapper.State.CONTENT
            }
        } else {
            dataWrapper.state = DataWrapper.State.EMPTY
        }

        liveData.value = dataWrapper
        isDataInitialized = true
    }

    private fun onFetchFailed(throwable: Throwable) {
        val dataWrapper = DataWrapper<T>()
        if (throwable is NoConnectivityException) {
            dataWrapper.state = DataWrapper.State.NO_INTERNET
        } else {
            dataWrapper.state = DataWrapper.State.ERROR
        }
        liveData.value = dataWrapper
    }

    override fun onCleared() {
        unSubscribe()
        super.onCleared()
    }

    private fun unSubscribe() {
        if (fetchSubscription?.isDisposed != true) {
            fetchSubscription?.dispose()
        }
    }
}
