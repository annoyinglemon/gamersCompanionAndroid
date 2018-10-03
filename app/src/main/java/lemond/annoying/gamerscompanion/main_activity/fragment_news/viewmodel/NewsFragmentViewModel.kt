package lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel

import lemond.annoying.gamerscompanion.main_activity.fragment_news.model.NewsRepository
import lemond.annoying.gamerscompanion.core.viewmodel.DataFetcherViewModel


class NewsFragmentViewModel(newsRepository: NewsRepository) : DataFetcherViewModel<List<NewsItemViewModel>>(newsRepository)
