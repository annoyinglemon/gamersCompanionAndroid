package lemond.annoying.gamerscompanion.app;


import dagger.Component;
import lemond.annoying.gamerscompanion.app.module.TestMainActivityModule;
import lemond.annoying.gamerscompanion.app.module.TestServiceModule;

@GamersApplicationScope
@Component(modules = {
        TestServiceModule.class
})
public interface TestGamersAppComponent extends GamersAppComponent {

    TestMainActivityComponent getViewControllerComponent(TestMainActivityModule testMainActivityModule);

}
