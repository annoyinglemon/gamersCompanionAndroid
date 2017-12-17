package lemond.annoying.gamerscompanion;

import android.app.Application;

import lemond.annoying.gamerscompanion.component.DaggerGamersAppComponent;
import lemond.annoying.gamerscompanion.component.GamersAppComponent;
import lemond.annoying.gamerscompanion.game.model.GameService;
import lemond.annoying.gamerscompanion.module.AppContextModule;
import timber.log.Timber;


public class GamersApplication extends Application {


    private GameService gameService;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        // include only the modules with constructor arguments, so we can remove the networkModule and gameServiceModule
        GamersAppComponent gamersAppComponent = DaggerGamersAppComponent.builder()
                .appContextModule(new AppContextModule(this))
                .build();
//                .networkModule(new NetworkModule())
//                .gameServiceModule(new GameServiceModule())
        gameService = gamersAppComponent.getGameService();
    }

    public GameService getGameService() {
        return gameService;
    }


}
