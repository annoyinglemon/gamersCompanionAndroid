package lemond.annoying.gamerscompanion.app;


import android.app.Application;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import lemond.annoying.gamerscompanion.app.module.AppContextModule;
import lemond.annoying.gamerscompanion.app.module.NetworkModule;
import lemond.annoying.gamerscompanion.app.module.ServiceModule;

// if you understand deeply what android dagger does, then remove this comment
// https://medium.com/@iammert/new-android-injector-with-dagger-2-part-1-8baa60152abe
// https://medium.com/@iammert/new-android-injector-with-dagger-2-part-2-4af05fd783d0
@GamersApplicationScope
@Component(modules = {
        AndroidInjectionModule.class,
        AppContextModule.class,
        NetworkModule.class,
        ServiceModule.class,
        ActivityBuilderModule.class
})
public interface GamersAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance Builder application(Application application);
        GamersAppComponent build();
    }

    void inject(GamersApplication app);

}

