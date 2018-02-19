package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.view;


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
import lemond.annoying.gamerscompanion.databinding.FragmentPopularBinding;
import lemond.annoying.gamerscompanion.fragment_now.adapter.GameGridAdapter;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel.PopularFragmentViewModel;

public class PopularFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private static final int GRID_COLUMNS_COUNT = 2;

    @Inject
    protected PopularFragmentViewModel viewModel;

    protected GameGridAdapter gameGridAdapter;

    private FragmentPopularBinding binding;

    public PopularFragment() {}

    public static PopularFragment newInstance() {
        return new PopularFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false);

        binding.swipeRefreshPopularFragment.setLayoutManager(new GridLayoutManager(getActivity(), GRID_COLUMNS_COUNT));
        binding.swipeRefreshPopularFragment.setOnRefreshListener(this);

        gameGridAdapter = new GameGridAdapter(GlideApp.with(this), getString(R.string.more_popular));

        binding.swipeRefreshPopularFragment.setAdapter(gameGridAdapter);

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
                binding.swipeRefreshPopularFragment.setDisplayState(listDataWrapper.state);
                binding.swipeRefreshPopularFragment.setRefreshing(false);
                refreshGridSpan();
            }
        });
    }

    private void refreshGridSpan() {
        ((GridLayoutManager) binding.swipeRefreshPopularFragment.getLayoutManager()).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
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
