package lemond.annoying.gamerscompanion.fragment_now.fragment_popular;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.app.GlideApp;
import lemond.annoying.gamerscompanion.databinding.GridItemGameBinding;
import lemond.annoying.gamerscompanion.repository.objects.Game;
import lemond.annoying.gamerscompanion.repository.util.ImageUtil;
import timber.log.Timber;


public class PopularAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // TODO: 2017-12-19 make this a list of gameViewModel later on for on click events
    private List<Game> popularGames;

    private final PopularFragment popularFragment;

    @Inject
    public PopularAdapter(PopularFragment popularFragment) {
        this.popularFragment = popularFragment;
        popularGames = new ArrayList<>();
    }

    public void setPopularGamesList(List<Game> popularGames) {
        this.popularGames.addAll(popularGames);
        notifyItemRangeInserted(0, popularGames.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GridItemGameBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.grid_item_game, parent, false);
        return new GameGridViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GameGridViewHolder gameGridViewHolder = ((GameGridViewHolder) holder);
        gameGridViewHolder.bindGame(popularGames.get(position));
//        GlideApp.with(popularFragment)
//                .load("https://images.igdb.com/igdb/image/upload/" + popularGames.get(position).cover.cloudinary_id+".jpg")
//                .centerCrop()
//                .placeholder(R.drawable.ic_placeholder_image)
//                .into(((GameGridViewHolder) holder).getBinding().gameGridItemImage);
        GlideApp.with(popularFragment)
                .load(ImageUtil.getLargeCoverImageUrl(popularGames.get(position).cover.url))
                .placeholder(R.drawable.ic_placeholder_image)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(gameGridViewHolder.getBinding().gameGridItemImage);
//        GlideApp.with(popularFragment)
//                .load("https://images.igdb.com/igdb/image/upload/" + popularGames.get(position).cover.cloudinary_id)
//                .
//                ;

    }

    @Override
    public int getItemCount() {
        return popularGames.size();
    }
}
