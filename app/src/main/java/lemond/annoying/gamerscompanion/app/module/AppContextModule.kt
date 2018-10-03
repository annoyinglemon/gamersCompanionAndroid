package lemond.annoying.gamerscompanion.app.module


import android.app.Application
import android.content.Context

import dagger.Module
import dagger.Provides
import lemond.annoying.gamerscompanion.app.GamersApplicationScope


@Module
class AppContextModule {

    @Provides
    @GamersApplicationScope
    internal fun provideContext(application: Application): Context {
        return application
    }

}
