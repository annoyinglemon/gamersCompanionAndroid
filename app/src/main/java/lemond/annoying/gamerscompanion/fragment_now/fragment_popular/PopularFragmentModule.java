package lemond.annoying.gamerscompanion.fragment_now.fragment_popular;


import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.repository.service.GameService;


@Module
public class PopularFragmentModule {

    private final PopularFragment popularFragment;

    public PopularFragmentModule(PopularFragment popularFragment) {
        this.popularFragment = popularFragment;
    }

    @Provides
    @PopularFragmentScope
    PopularFragment providePopularFragment() {
        return this.popularFragment;
    }

    @Provides
    @PopularFragmentScope
    PopularAdapter providePopularAdapter() {
        return new PopularAdapter(popularFragment);
    }
//
//    @Provides
//    @PopularFragmentScope
//    public PopularModel providePopularModel(GameService gameService) {
//        return new PopularModel(gameService);
//    }
//
//    @Provides
//    @PopularFragmentScope
//    public PopularViewModelFactory providePopularViewModelFactory(PopularModel popularModel) {
//        return new PopularViewModelFactory(popularModel);
//    }

}
