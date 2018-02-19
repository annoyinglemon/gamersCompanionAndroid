package lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.view;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.app.GlideApp;
import lemond.annoying.gamerscompanion.databinding.FragmentHypedBinding;
import lemond.annoying.gamerscompanion.fragment_now.adapter.GameGridAdapter;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.viewmodel.HypedFragmentViewModel;


public class HypedFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private static final int GRID_COLUMNS_COUNT = 2;

    @Inject
    protected HypedFragmentViewModel viewModel;

    protected GameGridAdapter gameGridAdapter;

    private FragmentHypedBinding binding;

    public HypedFragment() {}

    public static HypedFragment newInstance() {
        return new HypedFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_hyped, container, false);

        binding.swipeRefreshHypedFragment.setLayoutManager(new GridLayoutManager(getActivity(), GRID_COLUMNS_COUNT));
        binding.swipeRefreshHypedFragment.setOnRefreshListener(this);

        gameGridAdapter = new GameGridAdapter(GlideApp.with(this), getString(R.string.more_hyped));

        binding.swipeRefreshHypedFragment.setAdapter(gameGridAdapter);

        viewModel.fetchData(false);

        refreshGridSpan();

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getLiveData().observe(this, listDataWrapper -> {
            if (listDataWrapper != null) {
                gameGridAdapter.setDataList(listDataWrapper.data);
                binding.swipeRefreshHypedFragment.setDisplayState(listDataWrapper.state);
                binding.swipeRefreshHypedFragment.setRefreshing(false);
                refreshGridSpan();
            }
        });
    }

    private void refreshGridSpan() {
        ((GridLayoutManager) binding.swipeRefreshHypedFragment.getLayoutManager()).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return gameGridAdapter.getSpanSizeForGrid(position);
            }
        });
    }

    @Override
    public void onRefresh() {
        viewModel.fetchData(true);
    }

}
