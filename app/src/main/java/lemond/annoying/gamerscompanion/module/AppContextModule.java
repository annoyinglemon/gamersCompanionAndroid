package lemond.annoying.gamerscompanion.module;


import android.content.Context;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.GamersApplicationScope;

//provides the external module such as context
@Module
public class AppContextModule {

    private final Context context;
    // when this instances is created, context is required
    public AppContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @GamersApplicationScope
    public Context provideContext() {
        return context;
    }
}
