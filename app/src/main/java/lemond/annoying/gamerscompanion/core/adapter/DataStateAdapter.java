package lemond.annoying.gamerscompanion.core.adapter;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collection;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.databinding.ListItemViewMoreBinding;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.view.ViewMoreNewsViewHolder;


public abstract class DataStateAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public enum ViewType {
        CONTENT,
        VIEW_MORE
    }

    protected Collection<T> dataList;
    private final int columnCount;
    private final boolean isViewMoreVisible;

    protected DataStateAdapter(boolean isViewMoreVisible) {
        this(1, isViewMoreVisible);
    }

    protected DataStateAdapter(int columnCount, boolean isViewMoreVisible) {
        this.columnCount = columnCount;
        this.isViewMoreVisible = isViewMoreVisible;
    }

    public void setDataList(Collection<T> dataList) {
        int previousCount = getItemCount();
        this.dataList = dataList;
        notifyChanges(previousCount, getItemCount());
    }

    private void notifyChanges(int previousCount, int newCount) {
        if (previousCount == newCount) {
            notifyItemRangeChanged(0, newCount);
        } else if (previousCount > newCount) {
            notifyItemRangeChanged(0, newCount);
            notifyItemRangeRemoved(newCount, (previousCount - newCount));
        } else if (previousCount < newCount) {
            notifyItemRangeChanged(0, previousCount);
            notifyItemRangeInserted(previousCount, (newCount - previousCount));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewType type = ViewType.values()[viewType];
        switch (type) {
            case VIEW_MORE:
                return getViewMoreViewHolder(parent);
            default:
            case CONTENT:
                return getContentViewHolder(parent);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1 && isViewMoreVisible) {
            return ViewType.VIEW_MORE.ordinal();
        } else {
            return ViewType.CONTENT.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        int count = 0;

        if (isViewMoreVisible) {
            count++;
        }
        if (dataList != null) {
            count = count + dataList.size();
        }

        return count;
    }

    public int getSpanSizeForGrid(int position) {
        if (getItemViewType(position) == ViewType.CONTENT.ordinal()) {
            return 1;
        } else {
            return this.columnCount;
        }
    }

    /**
     * abstract method for custom content viewholder
     *
     * @param parent
     * @return
     */
    public abstract RecyclerView.ViewHolder getContentViewHolder(ViewGroup parent);

    /**
     * abstract method for custom view more viewholder, return null if view more button is disabled
     *
     * @param parent
     * @return
     */
    private RecyclerView.ViewHolder getViewMoreViewHolder(ViewGroup parent) {
        ListItemViewMoreBinding viewMoreBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_view_more, parent, false);
        return new ViewMoreNewsViewHolder(viewMoreBinding);
    }

}
