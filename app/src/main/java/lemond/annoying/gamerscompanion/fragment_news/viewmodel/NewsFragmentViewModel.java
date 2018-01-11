package lemond.annoying.gamerscompanion.fragment_news.viewmodel;



import java.util.List;
import lemond.annoying.gamerscompanion.fragment_news.model.NewsRepository;
import lemond.annoying.gamerscompanion.repository.viewmodel.DataFetcherViewModel;


public class NewsFragmentViewModel extends DataFetcherViewModel<List<NewsItemViewModel>> {

    NewsFragmentViewModel(NewsRepository newsRepository) {
        super(newsRepository);
    }
}
