package lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel;



import java.util.List;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.viewmodel.GameItemViewModel;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.model.NowPageRepository;
import lemond.annoying.gamerscompanion.core.viewmodel.DataFetcherViewModel;

public class NowPageFragmentViewModel extends DataFetcherViewModel<List<GameItemViewModel>> {

    public NowPageFragmentViewModel(NowPageRepository nowPageRepository) {
        super(nowPageRepository);
    }
}
