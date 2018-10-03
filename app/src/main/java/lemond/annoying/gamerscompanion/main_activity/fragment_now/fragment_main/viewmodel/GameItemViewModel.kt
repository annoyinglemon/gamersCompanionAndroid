package lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.viewmodel


import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.util.Pair
import android.view.View
import android.widget.ImageView

import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.game_activity.view.GameDetailsActivity
import lemond.annoying.gamerscompanion.repository.objects.Game
import lemond.annoying.gamerscompanion.core.util.ImageUtil

import lemond.annoying.gamerscompanion.game_activity.view.GameDetailsActivity.Companion.EXTRA_GAME

class GameItemViewModel(val game: Game) {

    val imageUrl: String
        get() = if (game.cover != null) {
            ImageUtil.getImageUrl(game.cover!!.cloudinary_id, ImageUtil.ImageSize.COVER_BIG, true)
        } else {
            ""
        }

    fun onGameItemClick(view: View) {
        val gameCoverImage = view.findViewById<ImageView>(R.id.imageview_game_cover)
        val gameDetailsIntent = Intent(view.context, GameDetailsActivity::class.java)
        val options = ActivityOptions.makeSceneTransitionAnimation(view.context as Activity, Pair.create(gameCoverImage, "game_cover_image"))
        gameDetailsIntent.putExtra(EXTRA_GAME, game)
        view.context.startActivity(gameDetailsIntent, options.toBundle())
    }
}
