package lemond.annoying.gamerscompanion.repository.adapter;


import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;



import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.databinding.RecyclerviewItemEmptyBinding;
import lemond.annoying.gamerscompanion.databinding.RecyclerviewItemErrorBinding;
import lemond.annoying.gamerscompanion.databinding.RecyclerviewItemLoadingBinding;
import lemond.annoying.gamerscompanion.databinding.RecyclerviewItemNoInternetBinding;
import lemond.annoying.gamerscompanion.repository.service.DataState;

import static lemond.annoying.gamerscompanion.repository.service.DataState.State.CONTENT;
import static lemond.annoying.gamerscompanion.repository.service.DataState.State.EMPTY;
import static lemond.annoying.gamerscompanion.repository.service.DataState.State.ERROR;
import static lemond.annoying.gamerscompanion.repository.service.DataState.State.NO_INTERNET;
import static lemond.annoying.gamerscompanion.repository.service.DataState.State.LOADING;

public abstract class DataStateAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected DataState<T> currentDataState;

    class StateViewHolder extends RecyclerView.ViewHolder {
        StateViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
        }
    }

    protected DataStateAdapter() {
        currentDataState = new DataState<>();
        currentDataState.state = LOADING;
        currentDataState.size = 1;
    }

    public void setCurrentDataState(DataState<T> dataState) {
        int previousCount = getItemCount();
        currentDataState.state = dataState.state;
        currentDataState.data = dataState.data;
        currentDataState.size = dataState.size;
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
        DataState.State state = DataState.State.values()[viewType];
        switch (state) {
            case ERROR:
                RecyclerviewItemErrorBinding errorBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_item_error, parent, false);
                return new StateViewHolder(errorBinding);
            case NO_INTERNET:
                RecyclerviewItemNoInternetBinding noInternetBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_item_no_internet, parent, false);
                return new StateViewHolder(noInternetBinding);
            case EMPTY:
                RecyclerviewItemEmptyBinding emptyBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_item_empty, parent, false);
                return new StateViewHolder(emptyBinding);
            case CONTENT:
                return getContentViewHolder(parent);
            default:
            case LOADING:
                RecyclerviewItemLoadingBinding loadingBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.recyclerview_item_loading, parent, false);
                return new StateViewHolder(loadingBinding);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            switch (currentDataState.state) {
                case ERROR:
                    return ERROR.ordinal();
                case NO_INTERNET:
                    return NO_INTERNET.ordinal();
                case EMPTY:
                    return EMPTY.ordinal();
                case CONTENT:
                    return CONTENT.ordinal();
                default:
                case LOADING:
                    return DataState.State.LOADING.ordinal();
            }
        } else {
            return CONTENT.ordinal();
        }
    }

    @Override
    public int getItemCount() {
        return currentDataState.size;
    }

    public int getSpanSizeForGrid(int position) {
        if (currentDataState.data != null && currentDataState.size > 0 && currentDataState.state == CONTENT || position > 0) {
            return 1;
        } else {
            return 2;
        }
    }

    public abstract RecyclerView.ViewHolder getContentViewHolder(ViewGroup parent);

}
