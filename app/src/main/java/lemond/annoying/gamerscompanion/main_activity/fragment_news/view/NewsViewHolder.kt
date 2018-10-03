package lemond.annoying.gamerscompanion.main_activity.fragment_news.view


import android.support.v7.widget.RecyclerView

import lemond.annoying.gamerscompanion.databinding.ListItemNewsBinding
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsItemViewModel

class NewsViewHolder(val binding: ListItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindPulseViewModel(newsItemViewModel: NewsItemViewModel) {
        binding.viewModel = newsItemViewModel
        binding.executePendingBindings()
    }
}
