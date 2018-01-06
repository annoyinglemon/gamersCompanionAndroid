package lemond.annoying.gamerscompanion.fragment_news.injection;

import dagger.Component;
import lemond.annoying.gamerscompanion.activity.injection.MainActivityComponent;
import lemond.annoying.gamerscompanion.fragment_news.view.NewsFragment;

@NewsFragmentScope
@Component(modules = NewsModule.class, dependencies = MainActivityComponent.class)
public interface NewsComponent {

    void injectNewsFragment(NewsFragment newsFragment);

}
