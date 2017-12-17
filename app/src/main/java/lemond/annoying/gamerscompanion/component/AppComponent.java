package lemond.annoying.gamerscompanion.component;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import lemond.annoying.gamerscompanion.GamersApplication;
import lemond.annoying.gamerscompanion.module.AppModule;
import lemond.annoying.gamerscompanion.now.viewmodel.NowViewModel;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class
})
public interface AppComponent {

    void inject(GamersApplication gamersApplication);

}
