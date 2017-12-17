package lemond.annoying.gamerscompanion.module;



import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.GamersApplicationScope;
import lemond.annoying.gamerscompanion.game.model.GameService;
import retrofit2.Retrofit;

// to let dagger know this is a module
//by including other modules, you can remove this included modules in the component declaration
@Module(includes = NetworkModule.class)
public class GameServiceModule {

    // app component talks to this module and this module says 'Tell me what you need'
    // on every method that provide dependency, we need the @Provide annotation
    @Provides
    // only can be used on gamersAppComponent and not on other component, it says, "im a single instance of the GamersAppComponent"
    @GamersApplicationScope
    public GameService provideGameService(Retrofit retrofit) {
        return retrofit.create(GameService.class);
    }

}
