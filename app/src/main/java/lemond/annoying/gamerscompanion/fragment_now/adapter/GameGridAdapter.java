package lemond.annoying.gamerscompanion.fragment_now.adapter;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import java.util.List;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.app.GlideRequests;
import lemond.annoying.gamerscompanion.databinding.GridItemGameBinding;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.viewmodel.GameItemViewModel;
import lemond.annoying.gamerscompanion.repository.adapter.DataStateAdapter;
import lemond.annoying.gamerscompanion.repository.service.DataWrapper;


public class GameGridAdapter extends DataStateAdapter<List<GameItemViewModel>> {


    private final GlideRequests glideRequests;

    public GameGridAdapter(GlideRequests glideRequests) {
        super(2);
        this.glideRequests = glideRequests;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == DataWrapper.State.CONTENT.ordinal()) {
            List<GameItemViewModel> itemViewModels = currentDataWrapper.data;
            GameItemViewModel gameItemViewModel = itemViewModels.get(position);
            GameGridViewHolder gameGridViewHolder = ((GameGridViewHolder) holder);
            gameGridViewHolder.bindItemViewModel(gameItemViewModel);
            glideRequests
                    .load(gameItemViewModel.getImageUrl())
                    .placeholder(R.drawable.ic_placeholder_image)
                    .error(R.drawable.ic_error_image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(gameGridViewHolder.getBinding().gameGridItemImage);
        }
    }

    @Override
    public RecyclerView.ViewHolder getContentViewHolder(ViewGroup parent) {
        GridItemGameBinding loadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.grid_item_game, parent, false);
        return new GameGridViewHolder(loadingBinding);
    }


}
