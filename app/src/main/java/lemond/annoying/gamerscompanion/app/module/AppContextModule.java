package lemond.annoying.gamerscompanion.app.module;


import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GamersApplicationScope;
import lemond.annoying.gamerscompanion.main_activity.injection.MainActivityComponent;

@Module(subcomponents = {
        MainActivityComponent.class
})
public class AppContextModule {


    @Provides
    @GamersApplicationScope
    Context provideContext(Application application) {
        return application;
    }

}
