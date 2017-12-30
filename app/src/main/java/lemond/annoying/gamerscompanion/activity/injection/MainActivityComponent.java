package lemond.annoying.gamerscompanion.activity.injection;


import dagger.Component;
import lemond.annoying.gamerscompanion.activity.view.MainActivity;
import lemond.annoying.gamerscompanion.app.GamersAppComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel.HypedViewModel;
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

    HypedViewModel getHypedViewModel();

}
