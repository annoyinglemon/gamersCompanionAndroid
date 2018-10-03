package lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel


import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import javax.inject.Inject

import lemond.annoying.gamerscompanion.main_activity.fragment_news.injection.NewsFragmentScope
import lemond.annoying.gamerscompanion.main_activity.fragment_news.model.NewsRepository

@NewsFragmentScope
class NewsFragmentViewModelFactory @Inject
internal constructor(private val newsRepository: NewsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsFragmentViewModel::class.java)) {
            return NewsFragmentViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
