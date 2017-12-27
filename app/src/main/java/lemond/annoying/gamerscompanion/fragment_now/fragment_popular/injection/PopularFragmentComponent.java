package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.injection;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import lemond.annoying.gamerscompanion.activity.MainActivityComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.view.PopularFragment;

@PopularFragmentScope
@Component(
        modules = {
                PopularFragmentModule.class,
                AndroidInjectionModule.class
        },
        dependencies = MainActivityComponent.class)
public interface PopularFragmentComponent {


    void injectPopularFragment(PopularFragment popularFragment);

}
