package lemond.annoying.gamerscompanion.app;

import android.content.Context;
import android.content.res.Resources;


import dagger.Component;
import dagger.android.AndroidInjectionModule;
import lemond.annoying.gamerscompanion.app.module.NewsServiceModule;
import lemond.annoying.gamerscompanion.repository.service.GameService;
import lemond.annoying.gamerscompanion.app.module.GameServiceModule;
import lemond.annoying.gamerscompanion.repository.service.NewsService;


@GamersApplicationScope
@Component(modules = {
        AndroidInjectionModule.class,
        GameServiceModule.class,
        NewsServiceModule.class
})
public interface GamersAppComponent {

    Context getApplicationContext();

    Resources getAppResources();

    GameService getGameService();

    NewsService getNewsService();

}
