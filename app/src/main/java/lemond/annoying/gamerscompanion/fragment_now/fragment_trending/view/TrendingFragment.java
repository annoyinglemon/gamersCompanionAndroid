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
import lemond.annoying.gamerscompanion.fragment_now.adapter.GameGridAdapter;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.NowFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.injection.DaggerTrendingComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.injection.TrendingModule;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.viewmodel.TrendingFragmentViewModel;


public class TrendingFragment extends Fragment {

    private static final int GRID_COLUMNS_COUNT = 2;

    @Inject
    protected TrendingFragmentViewModel viewModel;

    @Inject
    protected GameGridAdapter gameGridAdapter;

    private FragmentTrendingBinding binding;

    public TrendingFragment() {}

    public static TrendingFragment newInstance() {
        return new TrendingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerTrendingComponent.builder()
                .trendingModule(new TrendingModule(this))
                .nowFragmentComponent(((NowFragment) getParentFragment()).getComponent())
                .build().injectTrendingFragment(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getData().observe(this, dataWrapper -> {
            gameGridAdapter.setCurrentDataWrapper(dataWrapper);
            refreshGridSpan();
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_trending, container, false);
        binding.trendingGrid.setLayoutManager(new GridLayoutManager(getActivity(), GRID_COLUMNS_COUNT));
        binding.trendingGrid.setHasFixedSize(true);

        binding.trendingGrid.setAdapter(gameGridAdapter);

        viewModel.fetchData(false);

        refreshGridSpan();

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
