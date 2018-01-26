package lemond.annoying.gamerscompanion.app;


import dagger.Component;
import dagger.android.AndroidInjectionModule;
import lemond.annoying.gamerscompanion.app.module.AppContextModule;
import lemond.annoying.gamerscompanion.app.module.MainActivityModule;
import lemond.annoying.gamerscompanion.app.module.NetworkModule;
import lemond.annoying.gamerscompanion.app.module.ServiceModule;


@GamersApplicationScope
@Component(modules = {
        AndroidInjectionModule.class,
        AppContextModule.class,
        NetworkModule.class,
        ServiceModule.class,
})
public interface GamersAppComponent {

    ViewControllerComponent getViewControllerComponent(MainActivityModule mainActivityModule);

}
