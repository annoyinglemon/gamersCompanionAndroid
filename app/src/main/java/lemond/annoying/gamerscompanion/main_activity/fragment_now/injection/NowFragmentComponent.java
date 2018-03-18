package lemond.annoying.gamerscompanion.main_activity.fragment_now.injection;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.view.NowFragment;
import lemond.annoying.gamerscompanion.main_activity.injection.MainActivityScope;


//create news fragment scope and test
//@MainActivityScope
//Error:[NowFragmentComponent] NowFragmentComponent has conflicting scopes:
//      MainActivityComponent also has MainActivityScope
@Subcomponent(modules = {
        NowFragmentModule.class
})
public interface NowFragmentComponent extends AndroidInjector<NowFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<NowFragment> {}

}
