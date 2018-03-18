package lemond.annoying.gamerscompanion.app;


import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import lemond.annoying.gamerscompanion.app.module.AppContextModule;
import lemond.annoying.gamerscompanion.app.module.NetworkModule;
import lemond.annoying.gamerscompanion.app.module.ServiceModule;

//https://medium.com/@iammert/new-android-injector-with-dagger-2-part-1-8baa60152abe
@GamersApplicationScope
@Component(modules = {
        AndroidInjectionModule.class,
        AppContextModule.class,
        NetworkModule.class,
        ServiceModule.class,
        ActivityBuilder.class
})
public interface GamersAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        GamersAppComponent build();
    }

    void inject(GamersApplication app);

}

