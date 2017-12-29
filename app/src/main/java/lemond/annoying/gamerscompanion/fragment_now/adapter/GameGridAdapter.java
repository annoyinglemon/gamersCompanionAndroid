package lemond.annoying.gamerscompanion.fragment_now.adapter;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import java.util.List;
import javax.inject.Inject;
import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.app.GlideRequests;
import lemond.annoying.gamerscompanion.databinding.GridItemGameBinding;
import lemond.annoying.gamerscompanion.repository.adapter.DataStateAdapter;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import lemond.annoying.gamerscompanion.repository.service.DataState;
import lemond.annoying.gamerscompanion.repository.util.ImageUtil;


public class GameGridAdapter extends DataStateAdapter<List<Game>> {


    private final GlideRequests glideRequests;

    @Inject
    public GameGridAdapter(GlideRequests glideRequests) {
        this.glideRequests = glideRequests;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == DataState.State.CONTENT.ordinal()) {
            List<Game> data = currentDataState.data;
            Game game = data.get(position);
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
