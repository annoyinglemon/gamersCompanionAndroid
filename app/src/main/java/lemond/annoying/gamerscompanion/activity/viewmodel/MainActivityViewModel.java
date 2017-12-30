package lemond.annoying.gamerscompanion.activity.viewmodel;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    public static final int NOW_TAB_INDEX = 0;
    public static final int NEWS_TAB_INDEX = 1;
    public static final int SEARCH_TAB_INDEX = 2;

    private MutableLiveData<Integer> selectedPage;

    public MainActivityViewModel() {
        selectedPage = new MutableLiveData<>();
        this.selectedPage.setValue(NOW_TAB_INDEX);
    }

    public void setSelectedPage(int selectedPage) {
        this.selectedPage.setValue(selectedPage);
    }

    public MutableLiveData<Integer> getSelectedPage() {
        return selectedPage;
    }
}
