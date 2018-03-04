package lemond.annoying.gamerscompanion.app;


import dagger.Subcomponent;
import lemond.annoying.gamerscompanion.activity.view.MainActivity;
import lemond.annoying.gamerscompanion.app.module.MainActivityModule;
import lemond.annoying.gamerscompanion.fragment_news.view.NewsFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.view.NowFragment;

@ViewControllerScope
@Subcomponent(modules = MainActivityModule.class)
public interface ViewControllerComponent {

    void inject(MainActivity mainActivity);

    void inject(NowFragment nowFragment);

    void inject(NewsFragment newsFragment);

}
