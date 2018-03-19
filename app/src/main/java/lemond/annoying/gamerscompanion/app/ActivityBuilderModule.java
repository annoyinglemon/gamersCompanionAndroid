package lemond.annoying.gamerscompanion.app;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import lemond.annoying.gamerscompanion.main_activity.injection.MainActivityModule;
import lemond.annoying.gamerscompanion.main_activity.injection.MainActivityScope;
import lemond.annoying.gamerscompanion.main_activity.injection.MainFragmentBuilderModule;
import lemond.annoying.gamerscompanion.main_activity.view.MainActivity;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
            MainFragmentBuilderModule.class
    })
    @MainActivityScope
    abstract MainActivity bindMainActivity();

}