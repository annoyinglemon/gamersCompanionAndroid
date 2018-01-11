package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel;


import java.util.List;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_now.fragment_main.viewmodel.GameItemViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.model.PopularRepository;
import lemond.annoying.gamerscompanion.repository.viewmodel.DataFetcherViewModel;

public class PopularFragmentViewModel extends DataFetcherViewModel<List<GameItemViewModel>> {

    @Inject
    public PopularFragmentViewModel(PopularRepository popularRepository) {
        super(popularRepository);
    }

}
