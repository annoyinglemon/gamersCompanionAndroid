package lemond.annoying.gamerscompanion.main_activity.fragment_news.view;


import android.support.v7.widget.RecyclerView;

import lemond.annoying.gamerscompanion.databinding.ListItemNewsBinding;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsItemViewModel;

public class NewsViewHolder extends RecyclerView.ViewHolder {

    private ListItemNewsBinding binding;

    public NewsViewHolder(ListItemNewsBinding binding) {
        super(binding.getRoot());

        this.binding = binding;
    }

    public ListItemNewsBinding getBinding() {
        return binding;
    }

    public void bindPulseViewModel(NewsItemViewModel newsItemViewModel) {
        binding.setViewModel(newsItemViewModel);
        binding.executePendingBindings();
    }
}
