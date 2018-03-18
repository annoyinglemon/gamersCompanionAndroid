package lemond.annoying.gamerscompanion.app;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import lemond.annoying.gamerscompanion.app.module.AppContextModule;
import timber.log.Timber;


public class GamersApplication extends MultiDexApplication implements HasActivityInjector {

//    private GamersAppComponent gamersAppComponent;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
//        gamersAppComponent = DaggerGamersAppComponent.builder()
//                .appContextModule(new AppContextModule(this))
//                .build();
        DaggerGamersAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }

//    public static GamersApplication get(Activity activity) {
//        return (GamersApplication) activity.getApplication();
//    }

//    public GamersAppComponent getGamersAppComponent() {
//        return gamersAppComponent;
//    }

}
