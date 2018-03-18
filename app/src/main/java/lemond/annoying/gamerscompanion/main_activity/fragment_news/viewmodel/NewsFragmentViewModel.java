package lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel;



import java.util.List;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.model.NewsRepository;
import lemond.annoying.gamerscompanion.core.viewmodel.DataFetcherViewModel;


public class NewsFragmentViewModel extends DataFetcherViewModel<List<NewsItemViewModel>> {

    public NewsFragmentViewModel(NewsRepository newsRepository) {
        super(newsRepository);
    }
}
