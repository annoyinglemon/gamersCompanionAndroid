package lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.model;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.viewmodel.GameItemViewModel;
import lemond.annoying.gamerscompanion.core.model.DataRepository;
import lemond.annoying.gamerscompanion.repository.service.GameService;
import lemond.annoying.gamerscompanion.repository.objects.Game;


public class NowPageRepository implements DataRepository<List<GameItemViewModel>> {

    public enum PageType {
        TRENDING,
        POPULAR,
        HYPED
    }

    private final GameService gameService;
    private final PageType pageType;

    public NowPageRepository(GameService gameService, PageType pageType) {
        this.gameService = gameService;
        this.pageType = pageType;
    }

    @Override
    public Single<List<GameItemViewModel>> fetchData() {
        switch (pageType) {
            case TRENDING:
                return gameService.getTrendingGames().flatMap(games -> {
                    List<GameItemViewModel> gameItemViewModels = new ArrayList<>();
                    for (Game game : games) {
                        GameItemViewModel gameItemViewModel = new GameItemViewModel(game);
                        gameItemViewModels.add(gameItemViewModel);
                    }
                    return Single.just(gameItemViewModels);
                });
            case POPULAR:
                return gameService.getPopularGames(System.currentTimeMillis()).flatMap(games -> {
                    List<GameItemViewModel> gameItemViewModels = new ArrayList<>();
                    for (Game game : games) {
                        GameItemViewModel gameItemViewModel = new GameItemViewModel(game);
                        gameItemViewModels.add(gameItemViewModel);
                    }
                    return Single.just(gameItemViewModels);
                });
            default:
            case HYPED:
                return gameService.getHypedGames(System.currentTimeMillis()).flatMap(games -> {
                    List<GameItemViewModel> gameItemViewModels = new ArrayList<>();
                    for (Game game : games) {
                        GameItemViewModel gameItemViewModel = new GameItemViewModel(game);
                        gameItemViewModels.add(gameItemViewModel);
                    }
                    return Single.just(gameItemViewModels);
                });
        }

    }

}
