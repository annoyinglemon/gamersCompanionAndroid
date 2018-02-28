package lemond.annoying.gamerscompanion.fragment_now.fragment_page.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.databinding.FragmentNowPageBinding;
import lemond.annoying.gamerscompanion.fragment_now.adapter.GameGridAdapter;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.viewmodel.NowPageFragmentViewModel;

public abstract class NowPageFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final int GRID_COLUMNS_COUNT = 2;

    private NowPageFragmentViewModel viewModel;

    private FragmentNowPageBinding binding;

    protected GameGridAdapter gameGridAdapter;

    public NowPageFragment() {}

    public void setViewModel(NowPageFragmentViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.getLiveData().observe(this, listDataWrapper -> {
            if (listDataWrapper != null) {
                gameGridAdapter.setDataList(listDataWrapper.data);
                binding.swipeRefreshTrendingFragment.setDisplayState(listDataWrapper.state);
                binding.swipeRefreshTrendingFragment.setRefreshing(false);
                refreshGridSpan();
            }
        });
        this.viewModel.fetchData(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_now_page, container, false);

        binding.swipeRefreshTrendingFragment.setLayoutManager(new GridLayoutManager(getActivity(), GRID_COLUMNS_COUNT));
        binding.swipeRefreshTrendingFragment.setOnRefreshListener(this);

        initializeGridAdapter();

        binding.swipeRefreshTrendingFragment.setAdapter(gameGridAdapter);

        refreshGridSpan();

        return binding.getRoot();
    }

    private void refreshGridSpan() {
        ((GridLayoutManager) binding.swipeRefreshTrendingFragment.getLayoutManager()).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return gameGridAdapter.getSpanSizeForGrid(position);
            }
        });
    }

    @Override
    public void onRefresh() {
        if (viewModel != null) {
            viewModel.fetchData(true);
        }
    }

    public abstract void initializeGridAdapter();

}