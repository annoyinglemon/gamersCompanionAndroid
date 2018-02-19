package lemond.annoying.gamerscompanion.fragment_now.adapter;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import java.util.List;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.app.GlideRequests;
import lemond.annoying.gamerscompanion.core.viewmodel.ViewMoreViewModel;
import lemond.annoying.gamerscompanion.databinding.GridItemGameBinding;
import lemond.annoying.gamerscompanion.fragment_news.view.ViewMoreNewsViewHolder;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.viewmodel.GameItemViewModel;
import lemond.annoying.gamerscompanion.core.adapter.DataStateAdapter;
import lemond.annoying.gamerscompanion.repository.service.DataWrapper;


public class GameGridAdapter extends DataStateAdapter<GameItemViewModel> {

    private final GlideRequests glideRequests;
    private final String viewMoreText;

    public GameGridAdapter(GlideRequests glideRequests, String viewMoreText) {
        super(2, true);
        this.glideRequests = glideRequests;
        this.viewMoreText = viewMoreText;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ViewType.CONTENT.ordinal()) {
            List<GameItemViewModel> itemViewModels = (List<GameItemViewModel>) dataList;
            GameItemViewModel gameItemViewModel = itemViewModels.get(position);
            GameGridViewHolder gameGridViewHolder = ((GameGridViewHolder) holder);
            gameGridViewHolder.bindItemViewModel(gameItemViewModel);
            glideRequests
                    .load(gameItemViewModel.getImageUrl())
                    .placeholder(R.color.colorPrimaryVeryLight)
                    .error(R.drawable.ic_error_image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(gameGridViewHolder.getBinding().imageviewGameCover);
        } else if (getItemViewType(position) == ViewType.VIEW_MORE.ordinal()) {
            ViewMoreNewsViewHolder moreNewsViewHolder = ((ViewMoreNewsViewHolder) holder);
            moreNewsViewHolder.bindViewMoreViewModel(new ViewMoreViewModel(ViewMoreViewModel.ViewMoreType.NEWS, viewMoreText));
        }
    }

    @Override
    public RecyclerView.ViewHolder getContentViewHolder(ViewGroup parent) {
        GridItemGameBinding loadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.grid_item_game, parent, false);
        return new GameGridViewHolder(loadingBinding);
    }
}
