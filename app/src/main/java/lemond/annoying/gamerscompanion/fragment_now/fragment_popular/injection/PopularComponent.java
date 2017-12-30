package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.injection;


import dagger.Component;
import lemond.annoying.gamerscompanion.activity.injection.MainActivityComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.view.PopularFragment;

@PopularFragmentScope
@Component(
        modules = {
                PopularModule.class,
        },
        dependencies = MainActivityComponent.class)
public interface PopularComponent {

    void injectPopularFragment(PopularFragment popularFragment);

}
