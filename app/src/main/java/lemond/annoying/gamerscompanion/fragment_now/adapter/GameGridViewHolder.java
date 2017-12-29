package lemond.annoying.gamerscompanion.fragment_now.adapter;

import android.support.v7.widget.RecyclerView;

import lemond.annoying.gamerscompanion.databinding.GridItemGameBinding;
import lemond.annoying.gamerscompanion.repository.objects.Game;


public class GameGridViewHolder extends RecyclerView.ViewHolder {

    private GridItemGameBinding binding;

    public GameGridViewHolder(GridItemGameBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    public GridItemGameBinding getBinding() {
        return binding;
    }

    // TODO: 2017-12-19 change this to bindGameViewModel for gameViewModel binding
    public void bindGame(Game game) {
        binding.setGame(game);
        binding.executePendingBindings();
    }
}
