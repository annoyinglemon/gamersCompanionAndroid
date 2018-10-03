package lemond.annoying.gamerscompanion.app.module


import dagger.Module
import dagger.Provides
import lemond.annoying.gamerscompanion.app.GamersApplicationScope
import lemond.annoying.gamerscompanion.repository.service.GameService
import lemond.annoying.gamerscompanion.repository.service.NewsService
import retrofit2.Retrofit

@Module(includes = [NetworkModule::class])
class ServiceModule {

    @Provides
    @GamersApplicationScope
    internal fun provideNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    @GamersApplicationScope
    internal fun provideGameService(retrofit: Retrofit): GameService {
        return retrofit.create(GameService::class.java)
    }

}
