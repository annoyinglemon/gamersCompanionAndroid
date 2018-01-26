package lemond.annoying.gamerscompanion.app.module;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GamersApplicationScope;

@Module
public class AppContextModule {

    private final Context context;

    public AppContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @GamersApplicationScope
    Context provideContext() {
        return context;
    }

}
