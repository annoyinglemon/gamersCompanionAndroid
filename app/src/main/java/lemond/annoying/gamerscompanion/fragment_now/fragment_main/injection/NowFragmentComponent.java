package lemond.annoying.gamerscompanion.fragment_now.fragment_main.injection;

import dagger.Component;
import lemond.annoying.gamerscompanion.activity.injection.MainActivityComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel.HypedFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel.PopularFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel.TrendingFragmentViewModel;


@NowFragmentScope
@Component(
        modules = {
                NowModule.class,
        },
        dependencies = MainActivityComponent.class)
public interface NowFragmentComponent {

    TrendingFragmentViewModel getTrendingViewModel();

    PopularFragmentViewModel getPopularViewModel();

    HypedFragmentViewModel getHypedViewModel();

}
