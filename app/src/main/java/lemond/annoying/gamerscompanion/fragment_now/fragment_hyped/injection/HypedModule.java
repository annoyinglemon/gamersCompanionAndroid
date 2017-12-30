package lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.injection;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GlideApp;
import lemond.annoying.gamerscompanion.app.GlideRequests;
import lemond.annoying.gamerscompanion.fragment_now.adapter.GameGridAdapter;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.view.HypedFragment;

@Module
public class HypedModule {

    private final HypedFragment hypedFragment;

    public HypedModule(HypedFragment hypedFragment) {
        this.hypedFragment = hypedFragment;
    }

    @Provides
    @HypedFragmentScope
    public GlideRequests provideGlideRequests() {
        return GlideApp.with(hypedFragment);
    }

    @Provides
    @HypedFragmentScope
    public GameGridAdapter provideGameGridAdapter(GlideRequests glideRequests) {
        return new GameGridAdapter(glideRequests);
    }
}
