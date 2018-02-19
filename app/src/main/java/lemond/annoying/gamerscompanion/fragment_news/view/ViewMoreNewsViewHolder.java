package lemond.annoying.gamerscompanion.fragment_news.view;

import android.support.v7.widget.RecyclerView;

import lemond.annoying.gamerscompanion.core.viewmodel.ViewMoreViewModel;
import lemond.annoying.gamerscompanion.databinding.ListItemViewMoreBinding;


public class ViewMoreNewsViewHolder extends RecyclerView.ViewHolder {

    private ListItemViewMoreBinding binding;

    public ViewMoreNewsViewHolder(ListItemViewMoreBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindViewMoreViewModel(ViewMoreViewModel viewMoreViewModel) {
        binding.setViewModel(viewMoreViewModel);
        binding.executePendingBindings();
    }
}
