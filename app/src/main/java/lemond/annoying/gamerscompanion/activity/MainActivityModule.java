package lemond.annoying.gamerscompanion.activity;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.repository.service.GameService;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.PopularModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.PopularViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.PopularViewModelFactory;

@Module
public class MainActivityModule {

    private final MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public MainActivity provideMainActivity() {
        return mainActivity;
    }

    @Provides
    @MainActivityScope
    public FragmentManager provideFragmentManager() {
        return mainActivity.getSupportFragmentManager();
    }

    @Provides
    @MainActivityScope
    public PopularModel providePopularModel(GameService gameService) {
        return new PopularModel(gameService);
    }

    @Provides
    @MainActivityScope
    public PopularViewModelFactory providePopularViewModelFactory(PopularModel popularModel) {
        return new PopularViewModelFactory(popularModel);
    }

    @Provides
    @MainActivityScope
    public PopularViewModel providePopularViewModel(MainActivity mainActivity, PopularViewModelFactory popularViewModelFactory) {
        return ViewModelProviders.of(mainActivity, popularViewModelFactory).get(PopularViewModel.class);
    }

}
