package lemond.annoying.gamerscompanion.app;


import android.app.Activity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;
import lemond.annoying.gamerscompanion.main_activity.injection.MainActivityComponent;
import lemond.annoying.gamerscompanion.main_activity.view.MainActivity;

@Module
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivity(MainActivityComponent.Builder builder);

}