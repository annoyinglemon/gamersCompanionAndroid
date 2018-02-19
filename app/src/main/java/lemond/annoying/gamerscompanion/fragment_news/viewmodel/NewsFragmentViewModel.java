package lemond.annoying.gamerscompanion.fragment_news.viewmodel;



import java.util.List;
import lemond.annoying.gamerscompanion.fragment_news.model.NewsRepository;
import lemond.annoying.gamerscompanion.core.viewmodel.DataFetcherViewModel;


public class NewsFragmentViewModel extends DataFetcherViewModel<List<NewsItemViewModel>> {

    public NewsFragmentViewModel(NewsRepository newsRepository) {
        super(newsRepository);
    }
}
