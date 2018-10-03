package lemond.annoying.gamerscompanion.main_activity.fragment_news.view

import android.support.v7.widget.RecyclerView

import lemond.annoying.gamerscompanion.core.viewmodel.ViewMoreViewModel
import lemond.annoying.gamerscompanion.databinding.ListItemViewMoreBinding


class ViewMoreNewsViewHolder(private val binding: ListItemViewMoreBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindViewMoreViewModel(viewMoreViewModel: ViewMoreViewModel) {
        binding.viewModel = viewMoreViewModel
        binding.executePendingBindings()
    }
}
