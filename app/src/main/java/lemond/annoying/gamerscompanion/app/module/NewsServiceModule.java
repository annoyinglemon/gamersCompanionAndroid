package lemond.annoying.gamerscompanion.app.module;


import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GamersApplicationScope;
import lemond.annoying.gamerscompanion.repository.service.NewsService;
import retrofit2.Retrofit;

@Module(includes = NetworkModule.class)
public class NewsServiceModule {

    @Provides
    @GamersApplicationScope
    public NewsService provideNewsService(Retrofit retrofit) {
        return retrofit.create(NewsService.class);
    }

}
