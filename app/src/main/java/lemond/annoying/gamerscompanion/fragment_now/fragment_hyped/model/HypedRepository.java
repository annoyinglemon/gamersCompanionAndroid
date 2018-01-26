package lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.model;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import lemond.annoying.gamerscompanion.app.ViewControllerScope;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.viewmodel.GameItemViewModel;
import lemond.annoying.gamerscompanion.repository.model.DataRepository;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import lemond.annoying.gamerscompanion.repository.service.GameService;

@ViewControllerScope
public class HypedRepository implements DataRepository<List<GameItemViewModel>> {

    private final GameService gameService;

    @Inject
    HypedRepository(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public Single<List<GameItemViewModel>> fetchData() {
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
