package lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel;


import java.util.List;

import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.model.HypedRepository;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.viewmodel.GameItemViewModel;
import lemond.annoying.gamerscompanion.repository.viewmodel.DataFetcherViewModel;

public class HypedFragmentViewModel extends DataFetcherViewModel<List<GameItemViewModel>> {

    HypedFragmentViewModel(HypedRepository hypedRepository) {
        super(hypedRepository);
    }
}
