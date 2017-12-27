package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.injection;


import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GlideApp;
import lemond.annoying.gamerscompanion.app.GlideRequests;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.view.PopularAdapter;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.view.PopularFragment;


@Module
public class PopularFragmentModule {

    private final PopularFragment popularFragment;

    public PopularFragmentModule(PopularFragment popularFragment) {
        this.popularFragment = popularFragment;
    }

    @Provides
    @PopularFragmentScope
    public GlideRequests provideGlideRequests() {
        return GlideApp.with(popularFragment);
    }

    @Provides
    @PopularFragmentScope
    public PopularAdapter providePopularAdapter(GlideRequests glideRequests) {
        return new PopularAdapter(glideRequests);
    }

}
