package lemond.annoying.gamerscompanion.fragment_now.fragment_main.injection;

import dagger.Component;
import lemond.annoying.gamerscompanion.activity.injection.MainActivityComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel.HypedViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel.PopularViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel.TrendingViewModel;


@NowFragmentScope
@Component(
        modules = {
                NowModule.class,
        },
        dependencies = MainActivityComponent.class)
public interface NowFragmentComponent {

    TrendingViewModel getTrendingViewModel();

    PopularViewModel getPopularViewModel();

    HypedViewModel getHypedViewModel();

}
