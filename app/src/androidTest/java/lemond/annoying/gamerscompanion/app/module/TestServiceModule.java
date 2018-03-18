package lemond.annoying.gamerscompanion.app.module;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import lemond.annoying.gamerscompanion.app.GamersApplicationScope;
import lemond.annoying.gamerscompanion.repository.service.GameService;
import lemond.annoying.gamerscompanion.repository.service.NewsService;


@Module
public class TestServiceModule {

    @Provides
    @GamersApplicationScope
    NewsService provideMockNewsService() {
        return Mockito.mock(NewsService.class);
    }

    @Provides
    @GamersApplicationScope
    GameService provideMockGameService() {
        return Mockito.mock(GameService.class);
    }

}
