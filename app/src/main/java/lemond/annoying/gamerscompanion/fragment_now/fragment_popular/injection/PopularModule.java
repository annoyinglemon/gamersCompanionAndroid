package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.injection;


import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GlideApp;
import lemond.annoying.gamerscompanion.app.GlideRequests;
import lemond.annoying.gamerscompanion.fragment_now.adapter.GameGridAdapter;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.view.PopularFragment;

@Module
public class PopularModule {

    private final PopularFragment popularFragment;

    public PopularModule(PopularFragment popularFragment) {
        this.popularFragment = popularFragment;
    }

    @Provides
    @PopularFragmentScope
    public GlideRequests provideGlideRequests() {
        return GlideApp.with(popularFragment);
    }

    @Provides
    @PopularFragmentScope
    public GameGridAdapter provideGameGridAdapter(GlideRequests glideRequests) {
        return new GameGridAdapter(glideRequests);
    }

}
