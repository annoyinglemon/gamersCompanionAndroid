package lemond.annoying.gamerscompanion.main_activity.fragment_news.model

import java.util.ArrayList

import javax.inject.Inject

import io.reactivex.Single
import lemond.annoying.gamerscompanion.main_activity.fragment_news.injection.NewsFragmentScope
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsItemViewModel
import lemond.annoying.gamerscompanion.core.model.DataRepository
import lemond.annoying.gamerscompanion.repository.service.NewsService

@NewsFragmentScope
class NewsRepository @Inject
constructor(private val newsService: NewsService) : DataRepository<List<NewsItemViewModel>> {

    override fun fetchData(): Single<List<NewsItemViewModel>> {
        return newsService.getLatestNews(System.currentTimeMillis()).flatMap { pulses ->
            val newsItemViewModels = ArrayList<NewsItemViewModel>()
            for (pulse in pulses) {
                val newsItemViewModel = NewsItemViewModel(pulse)
                newsItemViewModels.add(newsItemViewModel)
            }
            Single.just<List<NewsItemViewModel>>(newsItemViewModels)
        }
    }
}

