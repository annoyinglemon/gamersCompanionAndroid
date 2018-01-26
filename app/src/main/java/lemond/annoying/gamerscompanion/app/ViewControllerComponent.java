package lemond.annoying.gamerscompanion.app;


import dagger.Subcomponent;
import lemond.annoying.gamerscompanion.app.module.MainActivityModule;
import lemond.annoying.gamerscompanion.fragment_news.view.NewsFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.view.HypedFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.view.PopularFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.view.TrendingFragment;

@ViewControllerScope
@Subcomponent(modules = MainActivityModule.class)
public interface ViewControllerComponent {

    void inject(NewsFragment newsFragment);

    void inject(PopularFragment popularFragment);

    void inject(TrendingFragment trendingFragment);

    void inject(HypedFragment hypedFragment);

}
