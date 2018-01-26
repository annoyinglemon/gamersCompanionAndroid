package lemond.annoying.gamerscompanion.app.module;


import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GamersApplicationScope;
import lemond.annoying.gamerscompanion.repository.service.GameService;
import lemond.annoying.gamerscompanion.repository.service.NewsService;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public class ServiceModule {

    @Provides
    @GamersApplicationScope
    NewsService provideNewsService(Retrofit retrofit) {
        return retrofit.create(NewsService.class);
    }

    @Provides
    @GamersApplicationScope
    GameService provideGameService(Retrofit retrofit) {
        return retrofit.create(GameService.class);
    }

}
