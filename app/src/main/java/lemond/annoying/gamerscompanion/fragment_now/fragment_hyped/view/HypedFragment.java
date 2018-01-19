package lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.view;


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
import lemond.annoying.gamerscompanion.databinding.FragmentHypedBinding;
import lemond.annoying.gamerscompanion.fragment_now.adapter.GameGridAdapter;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.injection.DaggerHypedComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.injection.HypedModule;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel.HypedFragmentViewModel;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.NowFragment;


public class HypedFragment extends Fragment {

    private static final int GRID_COLUMNS_COUNT = 2;

    @Inject
    protected HypedFragmentViewModel viewModel;

    @Inject
    protected GameGridAdapter gameGridAdapter;

    private FragmentHypedBinding binding;

    public HypedFragment() {}

    public static HypedFragment newInstance() {
        return new HypedFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerHypedComponent.builder()
                .hypedModule(new HypedModule(this))
                .nowFragmentComponent(((NowFragment) getParentFragment()).getComponent())
                .build().injectHypedFragment(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getData().observe(this, dataState -> {
            gameGridAdapter.setCurrentDataWrapper(dataState);
            refreshGridSpan();
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hyped, container, false);
        binding.hypedGrid.setLayoutManager(new GridLayoutManager(getActivity(), GRID_COLUMNS_COUNT));
        binding.hypedGrid.setHasFixedSize(true);

        binding.hypedGrid.setAdapter(gameGridAdapter);

        viewModel.fetchData(false);

        refreshGridSpan();

        return binding.getRoot();
    }

    private void refreshGridSpan() {
        ((GridLayoutManager) binding.hypedGrid.getLayoutManager()).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return gameGridAdapter.getSpanSizeForGrid(position);
            }
        });
    }

}