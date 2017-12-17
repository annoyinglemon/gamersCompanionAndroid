package lemond.annoying.gamerscompanion;

import android.app.Application;

import lemond.annoying.gamerscompanion.component.DaggerAppComponent;
import lemond.annoying.gamerscompanion.component.AppComponent;
import lemond.annoying.gamerscompanion.module.AppModule;


public class GamersApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

    public AppComponent getmAppComponent() {
        return mAppComponent;
    }

}
