package lemond.annoying.gamerscompanion.app.module;


import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GamersApplicationScope;


@Module
public class AppContextModule {

    @Provides
    @GamersApplicationScope
    Context provideContext(Application application) {
        return application;
    }

}
