package lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel

import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.viewmodel.GameItemViewModel
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.model.NowPageRepository
import lemond.annoying.gamerscompanion.core.viewmodel.DataFetcherViewModel


class NowPageFragmentViewModel(nowPageRepository: NowPageRepository) : DataFetcherViewModel<List<GameItemViewModel>>(nowPageRepository)
