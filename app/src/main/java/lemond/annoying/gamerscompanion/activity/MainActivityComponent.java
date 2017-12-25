package lemond.annoying.gamerscompanion.activity;


import dagger.Component;
import dagger.android.AndroidInjectionModule;
import lemond.annoying.gamerscompanion.app.GamersAppComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.PopularViewModel;

@MainActivityScope
@Component(
        modules = {
                MainActivityModule.class,
                AndroidInjectionModule.class
        },
        dependencies = GamersAppComponent.class)
public interface MainActivityComponent {

    void injectMainActivity(MainActivity mainActivity);

    PopularViewModel getPopularViewModel();

}
