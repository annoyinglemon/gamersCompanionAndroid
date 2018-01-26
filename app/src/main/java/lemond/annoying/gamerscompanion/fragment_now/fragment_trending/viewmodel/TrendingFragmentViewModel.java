package lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel;



import java.util.List;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.viewmodel.GameItemViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.model.TrendingRepository;
import lemond.annoying.gamerscompanion.repository.viewmodel.DataFetcherViewModel;


public class TrendingFragmentViewModel extends DataFetcherViewModel<List<GameItemViewModel>> {

    TrendingFragmentViewModel(TrendingRepository trendingRepository) {
        super(trendingRepository);
    }
}
