package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.injection;


import dagger.Component;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.injection.NowFragmentComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.view.PopularFragment;

@PopularFragmentScope
@Component(modules = PopularModule.class, dependencies = NowFragmentComponent.class)
public interface PopularComponent {

    void injectPopularFragment(PopularFragment popularFragment);

}
