package lemond.annoying.gamerscompanion.main_activity.fragment_now.adapter

import android.support.v7.widget.RecyclerView

import lemond.annoying.gamerscompanion.databinding.GridItemGameBinding
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.viewmodel.GameItemViewModel


class GameGridViewHolder(val binding: GridItemGameBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindItemViewModel(viewModel: GameItemViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}
