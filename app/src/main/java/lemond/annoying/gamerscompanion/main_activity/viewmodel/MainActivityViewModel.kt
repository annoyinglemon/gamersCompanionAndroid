package lemond.annoying.gamerscompanion.main_activity.viewmodel


import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    val selectedPageLiveData: MutableLiveData<Int> = MutableLiveData()
    private var previousSelected: Int? = 0

    init {
        this.selectedPageLiveData.value = NOW_TAB_INDEX
    }

    fun setSelectedPage(selectedPage: Int) {
        if (this.selectedPageLiveData.value != null) {
            previousSelected = this.selectedPageLiveData.value
        }
        this.selectedPageLiveData.value = selectedPage
    }

    fun shouldAnimateScrollToTop(): Boolean {
        return previousSelected == this.selectedPageLiveData.value
    }

    companion object {

        val NOW_TAB_INDEX = 0
        val NEWS_TAB_INDEX = 1
        val SEARCH_TAB_INDEX = 2
    }

}
