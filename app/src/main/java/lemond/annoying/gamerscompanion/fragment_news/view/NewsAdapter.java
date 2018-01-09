package lemond.annoying.gamerscompanion.fragment_news.view;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.app.GlideRequests;
import lemond.annoying.gamerscompanion.databinding.ListItemNewsBinding;
import lemond.annoying.gamerscompanion.fragment_news.injection.NewsFragmentScope;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsItemViewModel;
import lemond.annoying.gamerscompanion.repository.adapter.DataStateAdapter;
import lemond.annoying.gamerscompanion.repository.service.DataState;

@NewsFragmentScope
public class NewsAdapter extends DataStateAdapter<List<NewsItemViewModel>>{

    private final GlideRequests glideRequests;

    @Inject
    public NewsAdapter(GlideRequests glideRequests) {
        this.glideRequests = glideRequests;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == DataState.State.CONTENT.ordinal()) {
            List<NewsItemViewModel> data = currentDataState.data;
            NewsItemViewModel newsItemViewModel = data.get(position);
            NewsViewHolder newsViewHolder = ((NewsViewHolder) holder);
            newsViewHolder.bindPulseViewModel(newsItemViewModel);
            glideRequests
                    .load(newsItemViewModel.getPulse().image)
                    .placeholder(R.drawable.ic_placeholder_image)
                    .error(R.drawable.ic_error_image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(newsViewHolder.getBinding().imageNewsImageview);
        }
    }

    @Override
    public RecyclerView.ViewHolder getContentViewHolder(ViewGroup parent) {
        ListItemNewsBinding loadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_news, parent, false);
        return new NewsViewHolder(loadingBinding);
    }

}
