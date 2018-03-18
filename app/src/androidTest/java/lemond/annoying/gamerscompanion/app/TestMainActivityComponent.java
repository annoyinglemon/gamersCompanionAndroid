package lemond.annoying.gamerscompanion.app;


import dagger.Subcomponent;
import lemond.annoying.gamerscompanion.app.module.TestMainActivityModule;
import lemond.annoying.gamerscompanion.main_activity.injection.MainActivityComponent;
import lemond.annoying.gamerscompanion.main_activity.injection.MainActivityScope;

@MainActivityScope
@Subcomponent(modules = TestMainActivityModule.class)
public interface TestMainActivityComponent extends MainActivityComponent {

}
