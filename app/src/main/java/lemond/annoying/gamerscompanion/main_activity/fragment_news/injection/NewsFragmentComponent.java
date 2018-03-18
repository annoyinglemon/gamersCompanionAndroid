package lemond.annoying.gamerscompanion.main_activity.fragment_news.injection;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.view.NewsFragment;
import lemond.annoying.gamerscompanion.main_activity.injection.MainActivityScope;


//create news fragment scope and test
//@MainActivityScope
//Error:[NowFragmentComponent] NowFragmentComponent has conflicting scopes:
//      MainActivityComponent also has MainActivityScope
@Subcomponent(modules = {
        NewsFragmentModule.class
})
public interface NewsFragmentComponent extends AndroidInjector<NewsFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<NewsFragment> {}

}
