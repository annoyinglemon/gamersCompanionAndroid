package lemond.annoying.gamerscompanion.game_activity.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import lemond.annoying.gamerscompanion.game_activity.model.GameDetailsActivityRepository

@Suppress("UNCHECKED_CAST")
class GameDetailsViewModelFactory(private val gameDetailsActivityRepository: GameDetailsActivityRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameDetailsActivityViewModel::class.java)) {
            return GameDetailsActivityViewModel(gameDetailsActivityRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}