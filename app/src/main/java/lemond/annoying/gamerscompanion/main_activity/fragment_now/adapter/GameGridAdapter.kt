package lemond.annoying.gamerscompanion.main_activity.fragment_now.adapter


import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup

import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.app.GlideRequests
import lemond.annoying.gamerscompanion.core.viewmodel.ViewMoreViewModel
import lemond.annoying.gamerscompanion.databinding.GridItemGameBinding
import lemond.annoying.gamerscompanion.main_activity.fragment_news.view.ViewMoreNewsViewHolder
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.viewmodel.GameItemViewModel
import lemond.annoying.gamerscompanion.core.adapter.DataStateAdapter


class GameGridAdapter(private val glideRequests: GlideRequests, private val viewMoreText: String) : DataStateAdapter<GameItemViewModel>(2, true) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DataStateAdapter.ViewType.CONTENT.ordinal) {
            val itemViewModels = getDataList() as List<GameItemViewModel>?
            val gameItemViewModel = itemViewModels!![position]
            val gameGridViewHolder = holder as GameGridViewHolder
            gameGridViewHolder.bindItemViewModel(gameItemViewModel)
            if (!TextUtils.isEmpty(gameItemViewModel.imageUrl)) {
                glideRequests
                        .load(gameItemViewModel.imageUrl)
                        .placeholder(R.color.colorPrimaryVeryLight)
                        .error(R.drawable.ic_error_image)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(gameGridViewHolder.binding.imageviewGameCover)
            }

        } else if (getItemViewType(position) == DataStateAdapter.ViewType.VIEW_MORE.ordinal) {
            val moreNewsViewHolder = holder as ViewMoreNewsViewHolder
            moreNewsViewHolder.bindViewMoreViewModel(ViewMoreViewModel(ViewMoreViewModel.ViewMoreType.NEWS, viewMoreText))
        }
    }

    override fun getContentViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val loadingBinding = DataBindingUtil.inflate<GridItemGameBinding>(LayoutInflater.from(parent.context), R.layout.grid_item_game, parent, false)
        return GameGridViewHolder(loadingBinding)
    }
}
