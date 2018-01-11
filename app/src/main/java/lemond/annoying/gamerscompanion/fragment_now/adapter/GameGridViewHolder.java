package lemond.annoying.gamerscompanion.fragment_now.adapter;

import android.support.v7.widget.RecyclerView;

import lemond.annoying.gamerscompanion.databinding.GridItemGameBinding;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.viewmodel.GameItemViewModel;


public class GameGridViewHolder extends RecyclerView.ViewHolder {

    private GridItemGameBinding binding;

    public GameGridViewHolder(GridItemGameBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    public GridItemGameBinding getBinding() {
        return binding;
    }

    public void bindItemViewModel(GameItemViewModel viewModel) {
        binding.setViewModel(viewModel);
        binding.executePendingBindings();
    }
}
