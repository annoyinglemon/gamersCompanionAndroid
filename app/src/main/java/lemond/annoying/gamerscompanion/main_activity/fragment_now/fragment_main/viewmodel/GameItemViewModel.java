package lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.viewmodel;


import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import lemond.annoying.gamerscompanion.game_activity.GameActivity;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import lemond.annoying.gamerscompanion.core.util.ImageUtil;

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

    public String getThemes() {
        String themes = "";
        if (game.themes != null) {
            for (int i = 0; i < game.themes.size(); i++) {
                themes = themes.concat(game.themes.get(i).name);
                if (i < game.themes.size() - 1) {
                    themes = themes.concat(", ");
                }
            }
        }
        return themes;
    }

    public void onGameItemClick(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), GameActivity.class));
        Toast.makeText(view.getContext(), game.name, Toast.LENGTH_SHORT).show();
    }
}
