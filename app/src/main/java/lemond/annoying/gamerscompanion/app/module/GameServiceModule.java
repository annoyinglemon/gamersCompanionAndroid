package lemond.annoying.gamerscompanion.app.module;



import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GamersApplicationScope;
import lemond.annoying.gamerscompanion.repository.service.GameService;
import retrofit2.Retrofit;


@Module(includes = NetworkModule.class)
public class GameServiceModule {

    @Provides
    @GamersApplicationScope
    GameService provideGameService(Retrofit retrofit) {
        return retrofit.create(GameService.class);
    }

}
