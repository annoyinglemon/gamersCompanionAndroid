package lemond.annoying.gamerscompanion.core.viewmodel


import android.view.View
import android.widget.Toast

class ViewMoreViewModel(private val type: ViewMoreType, val viewMoreText: String) {

    enum class ViewMoreType {
        NEWS,
        HYPED_GAMES,
        POPULAR_GAMES,
        TRENDING_GAMES
    }

    fun onViewMoreClick(view: View) {
        when (type) {
            ViewMoreViewModel.ViewMoreType.HYPED_GAMES -> {
            }
            ViewMoreViewModel.ViewMoreType.POPULAR_GAMES -> {
            }
            ViewMoreViewModel.ViewMoreType.TRENDING_GAMES -> {
            }
            ViewMoreViewModel.ViewMoreType.NEWS ->
                // TODO: 2018-02-16 launch view more news activity here
                Toast.makeText(view.context, "to be implemented", Toast.LENGTH_SHORT).show()
        }
    }
}
