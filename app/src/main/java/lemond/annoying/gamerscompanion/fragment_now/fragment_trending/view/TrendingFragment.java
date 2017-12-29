package lemond.annoying.gamerscompanion.fragment_now.fragment_trending.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.databinding.FragmentTrendingBinding;
import lemond.annoying.gamerscompanion.activity.MainActivity;
import lemond.annoying.gamerscompanion.fragment_now.adapter.GameGridAdapter;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.injection.DaggerTrendingComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.injection.TrendingComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.injection.TrendingModule;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel.TrendingViewModel;


public class TrendingFragment extends Fragment {

    private static final int GRID_COLUMNS_COUNT = 2;

    @Inject
    public TrendingViewModel viewModel;

    @Inject
    public GameGridAdapter gameGridAdapter;

    private FragmentTrendingBinding binding;

    public TrendingFragment() {}

    public static TrendingFragment newInstance() {
        return new TrendingFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getTrendingGames().observe(this, dataState -> {
            gameGridAdapter.setCurrentDataState(dataState);
            refreshGridSpan();
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trending, container, false);
        binding.trendingGrid.setLayoutManager(new GridLayoutManager(getActivity(), GRID_COLUMNS_COUNT));
        binding.trendingGrid.setHasFixedSize(true);

        TrendingComponent component = DaggerTrendingComponent.builder()
                .trendingModule(new TrendingModule(this))
                .mainActivityComponent(((MainActivity) getActivity()).getComponent())
                .build();

        component.injectTrendingFragment(this);

        binding.trendingGrid.setAdapter(gameGridAdapter);
        refreshGridSpan();

        viewModel.refreshData(false);

        return binding.getRoot();
    }

    private void refreshGridSpan() {
        ((GridLayoutManager) binding.trendingGrid.getLayoutManager()).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return gameGridAdapter.getSpanSizeForGrid(position);
            }
        });
    }

}
