package lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.injection;


import dagger.Component;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.view.HypedFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.injection.NowFragmentComponent;

@HypedFragmentScope
@Component(modules = HypedModule.class, dependencies = NowFragmentComponent.class)
public interface HypedComponent {

    void injectHypedFragment(HypedFragment hypedFragment);

}