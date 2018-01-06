package lemond.annoying.gamerscompanion.fragment_now.fragment_trending.injection;

import dagger.Component;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.injection.NowFragmentComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.view.TrendingFragment;

@TrendingFragmentScope
@Component(modules = TrendingModule.class, dependencies = NowFragmentComponent.class)
public interface TrendingComponent {

    void injectTrendingFragment(TrendingFragment trendingFragment);

}
