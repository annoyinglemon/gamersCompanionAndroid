package lemond.annoying.gamerscompanion.app;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import lemond.annoying.gamerscompanion.app.module.AppContextModule;
import timber.log.Timber;


public class GamersApplication extends MultiDexApplication {

    private GamersAppComponent gamersAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
        gamersAppComponent = DaggerGamersAppComponent.builder()
                .appContextModule(new AppContextModule(this))
                .build();

    }

    public static GamersApplication get(Activity activity) {
        return (GamersApplication) activity.getApplication();
    }

    public GamersAppComponent getGamersAppComponent() {
        return gamersAppComponent;
    }

}
