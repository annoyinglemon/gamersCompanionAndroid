package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.view;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;



import javax.inject.Inject;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.app.GlideRequests;
import lemond.annoying.gamerscompanion.databinding.GridItemGameBinding;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.ContentDataStateAdapter;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import lemond.annoying.gamerscompanion.repository.util.ImageUtil;


public class PopularAdapter extends ContentDataStateAdapter<Game> {


    private final GlideRequests glideRequests;

    @Inject
    public PopularAdapter(GlideRequests glideRequests) {
        this.glideRequests = glideRequests;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == State.CONTENT.ordinal()) {
            Game game = dataList.get(position);
            GameGridViewHolder gameGridViewHolder = ((GameGridViewHolder) holder);
            gameGridViewHolder.bindGame(game);
            glideRequests
                    .load(ImageUtil.getLargeCoverImageUrl(game.cover.url))
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
