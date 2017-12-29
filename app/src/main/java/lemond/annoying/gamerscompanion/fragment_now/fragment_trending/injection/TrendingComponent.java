package lemond.annoying.gamerscompanion.fragment_now.fragment_trending.injection;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import lemond.annoying.gamerscompanion.activity.MainActivityComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.view.TrendingFragment;

@TrendingFragmentScope
@Component(
        modules = {
                TrendingModule.class,
        },
        dependencies = MainActivityComponent.class)
public interface TrendingComponent {


    void injectTrendingFragment(TrendingFragment trendingFragment);

}
