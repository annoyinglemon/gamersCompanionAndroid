package lemond.annoying.gamerscompanion.activity.injection;


import android.content.res.Resources;

import dagger.Component;
import lemond.annoying.gamerscompanion.app.GamersAppComponent;
import lemond.annoying.gamerscompanion.repository.service.GameService;
import lemond.annoying.gamerscompanion.repository.service.NewsService;

@MainActivityScope
@Component(dependencies = GamersAppComponent.class)
public interface MainActivityComponent {

    Resources getAppResources();

    GameService getGameService();

    NewsService getNewsService();

}
