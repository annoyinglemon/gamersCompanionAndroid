package lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.viewmodel;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.game_activity.view.GameDetailsActivity;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import lemond.annoying.gamerscompanion.core.util.ImageUtil;

import static lemond.annoying.gamerscompanion.game_activity.view.GameDetailsActivity.EXTRA_GAME;

public class GameItemViewModel {

    private final Game game;

    public GameItemViewModel(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public String getImageUrl() {
        if (game.getCover() != null) {
            return ImageUtil.getImageUrl(game.getCover().cloudinary_id, ImageUtil.ImageSize.COVER_BIG, true);
        } else {
            return "";
        }
    }

    public void onGameItemClick(View view) {
        ImageView gameCoverImage = view.findViewById(R.id.imageview_game_cover);
        Intent gameDetailsIntent = new Intent(view.getContext(), GameDetailsActivity.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(), Pair.create(gameCoverImage, "game_cover_image"));
        gameDetailsIntent.putExtra(EXTRA_GAME, game);
        view.getContext().startActivity(gameDetailsIntent, options.toBundle());
    }
}
