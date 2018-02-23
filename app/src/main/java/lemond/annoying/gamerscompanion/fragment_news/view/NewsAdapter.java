package lemond.annoying.gamerscompanion.fragment_news.view;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.app.GlideRequests;
import lemond.annoying.gamerscompanion.core.util.ImageUtil;
import lemond.annoying.gamerscompanion.core.viewmodel.ViewMoreViewModel;
import lemond.annoying.gamerscompanion.databinding.ListItemNewsBinding;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsItemViewModel;
import lemond.annoying.gamerscompanion.core.adapter.DataStateAdapter;

public class NewsAdapter extends DataStateAdapter<NewsItemViewModel>{

    private final GlideRequests glideRequests;
    private final String viewMoreText;

    NewsAdapter(GlideRequests glideRequests, String viewMoreText) {
        super(true);
        this.glideRequests = glideRequests;
        this.viewMoreText = viewMoreText;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == ViewType.CONTENT.ordinal()) {
            List<NewsItemViewModel> newsList = (List<NewsItemViewModel>) dataList;
            NewsItemViewModel newsItemViewModel = newsList.get(position);
            NewsViewHolder newsViewHolder = ((NewsViewHolder) holder);
            newsViewHolder.bindPulseViewModel(newsItemViewModel);
            glideRequests
                    .load(ImageUtil.getWebsiteFavIcon(newsItemViewModel.getBaseUrl()))
                    .placeholder(R.color.white)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .apply(RequestOptions.circleCropTransform())
                    .into(newsViewHolder.getBinding().imageViewWebsiteFavIcon);
            glideRequests
                    .load(newsItemViewModel.getPulse().image)
                    .placeholder(R.color.colorPrimaryVeryLight)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(newsViewHolder.getBinding().imageviewItemNews);
        } else if (getItemViewType(position) == ViewType.VIEW_MORE.ordinal()) {
            ViewMoreNewsViewHolder moreNewsViewHolder = ((ViewMoreNewsViewHolder) holder);
            moreNewsViewHolder.bindViewMoreViewModel(new ViewMoreViewModel(ViewMoreViewModel.ViewMoreType.NEWS, viewMoreText));
        }
    }

    @Override
    public RecyclerView.ViewHolder getContentViewHolder(ViewGroup parent) {
        ListItemNewsBinding newsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_news, parent, false);
        return new NewsViewHolder(newsBinding);
    }
}
