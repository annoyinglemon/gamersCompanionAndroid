package lemond.annoying.gamerscompanion.component;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import lemond.annoying.gamerscompanion.GamersApplicationScope;
import lemond.annoying.gamerscompanion.game.model.GameService;
import lemond.annoying.gamerscompanion.module.AppContextModule;
import lemond.annoying.gamerscompanion.module.GameServiceModule;


// component to find out that this class is part of dependency injection
@Singleton
// this modules locks in this component by scope
@GamersApplicationScope
@Component(modules = {
        AndroidInjectionModule.class,
        GameServiceModule.class,
})
public interface GamersAppComponent {

    // component needs to provide gamerService instance
    // we tell the component that the module 'GamerServiceModule.class' above is required
    // in order for this method to return GameService
    GameService getGameService();

//    void inject(GamersApplication gamersApplication);

}
