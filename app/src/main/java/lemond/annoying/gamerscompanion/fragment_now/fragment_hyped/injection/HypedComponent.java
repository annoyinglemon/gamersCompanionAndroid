package lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.injection;


import dagger.Component;
import lemond.annoying.gamerscompanion.activity.injection.MainActivityComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.view.HypedFragment;

@HypedFragmentScope
@Component(
        modules = {
                HypedModule.class,
        },
        dependencies = MainActivityComponent.class)
public interface HypedComponent {

    void injectHypedFragment(HypedFragment hypedFragment);

}