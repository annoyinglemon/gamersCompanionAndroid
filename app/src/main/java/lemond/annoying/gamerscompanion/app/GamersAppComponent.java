package lemond.annoying.gamerscompanion.app;

import android.content.Context;


import dagger.Component;
import dagger.android.AndroidInjectionModule;
import lemond.annoying.gamerscompanion.repository.service.GameService;
import lemond.annoying.gamerscompanion.app.module.GameServiceModule;


// component to find out that this class is part of dependency injection
// this modules locks in this component by scope
@GamersApplicationScope
@Component(modules = {
        AndroidInjectionModule.class,
        GameServiceModule.class,
})
public interface GamersAppComponent {

    Context getApplicationContext();

    GameService getGameService();

}
