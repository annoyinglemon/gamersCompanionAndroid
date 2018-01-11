package lemond.annoying.gamerscompanion.fragment_now.fragment_main.viewmodel;


import android.view.View;
import android.widget.Toast;

import lemond.annoying.gamerscompanion.repository.objects.Game;
import lemond.annoying.gamerscompanion.repository.util.ImageUtil;

public class GameItemViewModel {

    private final Game game;

    public GameItemViewModel(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public String getImageUrl() {
        return ImageUtil.getImageUrl(game.cover.cloudinary_id, ImageUtil.ImageSize.COVER_BIG, true);
    }

    public void onGameItemClick(View view) {
        Toast.makeText(view.getContext(), game.name, Toast.LENGTH_SHORT).show();
    }
}
