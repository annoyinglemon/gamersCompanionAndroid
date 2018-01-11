package lemond.annoying.gamerscompanion.fragment_now.fragment_trending.injection;


import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GlideApp;
import lemond.annoying.gamerscompanion.app.GlideRequests;
import lemond.annoying.gamerscompanion.fragment_now.adapter.GameGridAdapter;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.view.TrendingFragment;


@Module
public class TrendingModule {

    private final TrendingFragment trendingFragment;

    public TrendingModule(TrendingFragment trendingFragment) {
        this.trendingFragment = trendingFragment;
    }

    @Provides
    @TrendingFragmentScope
    GlideRequests provideGlideRequests() {
        return GlideApp.with(trendingFragment);
    }

    @Provides
    @TrendingFragmentScope
    GameGridAdapter provideGameGridAdapter(GlideRequests glideRequests) {
        return new GameGridAdapter(glideRequests);
    }

}
