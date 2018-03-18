package lemond.annoying.gamerscompanion.main_activity.injection;


import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import lemond.annoying.gamerscompanion.main_activity.view.MainActivity;

@MainActivityScope
@Subcomponent(modules = {
        MainActivityModule.class,
        FragmentBuilderModule.class
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {}

}
