package lemond.annoying.gamerscompanion.activity;


import dagger.Component;
import dagger.android.AndroidInjectionModule;
import lemond.annoying.gamerscompanion.app.GamersAppComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel.PopularViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel.TrendingViewModel;

@MainActivityScope
@Component(
        modules = {
                MainActivityModule.class,
        },
        dependencies = GamersAppComponent.class)
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);

    TrendingViewModel getPopularViewModel();

    PopularViewModel getPopulareViewModel();

}
