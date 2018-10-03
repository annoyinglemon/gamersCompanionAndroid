package lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.view

import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.app.GlideApp
import lemond.annoying.gamerscompanion.main_activity.fragment_now.adapter.GameGridAdapter


class HypedPageFragment : NowPageFragment() {

    override fun initializeGridAdapter() {
        gameGridAdapter = GameGridAdapter(GlideApp.with(this), getString(R.string.more_hyped))
    }

    companion object {
        fun newInstance(): HypedPageFragment {
            return HypedPageFragment()
        }
    }

}
