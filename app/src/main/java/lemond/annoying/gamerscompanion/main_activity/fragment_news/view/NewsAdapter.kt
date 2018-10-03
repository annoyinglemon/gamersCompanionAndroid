package lemond.annoying.gamerscompanion.main_activity.fragment_news.view


import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.app.GlideRequests
import lemond.annoying.gamerscompanion.core.util.ImageUtil
import lemond.annoying.gamerscompanion.core.viewmodel.ViewMoreViewModel
import lemond.annoying.gamerscompanion.databinding.ListItemNewsBinding
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsItemViewModel
import lemond.annoying.gamerscompanion.core.adapter.DataStateAdapter

class NewsAdapter internal constructor(private val glideRequests: GlideRequests, private val viewMoreText: String) : DataStateAdapter<NewsItemViewModel>(true) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DataStateAdapter.ViewType.CONTENT.ordinal) {

            val newsList = getDataList() as List<NewsItemViewModel>?
            val newsItemViewModel = newsList!![position]
            val newsViewHolder = holder as NewsViewHolder

            newsViewHolder.bindPulseViewModel(newsItemViewModel)
            glideRequests
                    .load(ImageUtil.getWebsiteFavIcon(newsItemViewModel.baseUrl!!))
                    .placeholder(R.color.white)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .apply(RequestOptions.circleCropTransform())
                    .into(newsViewHolder.binding.imageViewWebsiteFavIcon)

            glideRequests
                    .load(newsItemViewModel.pulse.image)
                    .placeholder(R.color.colorPrimaryVeryLight)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(newsViewHolder.binding.imageviewItemNews)

        } else if (getItemViewType(position) == DataStateAdapter.ViewType.VIEW_MORE.ordinal) {
            val moreNewsViewHolder = holder as ViewMoreNewsViewHolder
            moreNewsViewHolder.bindViewMoreViewModel(ViewMoreViewModel(ViewMoreViewModel.ViewMoreType.NEWS, viewMoreText))
        }
    }

    override fun getContentViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val newsBinding = DataBindingUtil.inflate<ListItemNewsBinding>(LayoutInflater.from(parent.context), R.layout.list_item_news, parent, false)
        return NewsViewHolder(newsBinding)
    }
}
