package lemond.annoying.gamerscompanion.fragment_now.fragment_popular.view;


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
import lemond.annoying.gamerscompanion.databinding.FragmentPopularBinding;
import lemond.annoying.gamerscompanion.fragment_now.adapter.GameGridAdapter;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.NowFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.injection.DaggerPopularComponent;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.injection.PopularModule;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.viewmodel.PopularFragmentViewModel;

public class PopularFragment extends Fragment {

    private static final int GRID_COLUMNS_COUNT = 2;

    @Inject
    protected PopularFragmentViewModel viewModel;

    @Inject
    protected GameGridAdapter gameGridAdapter;

    private FragmentPopularBinding binding;

    public PopularFragment() {}

    public static PopularFragment newInstance() {
        return new PopularFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerPopularComponent.builder()
                .popularModule(new PopularModule(this))
                .nowFragmentComponent(((NowFragment) getParentFragment()).getComponent())
                .build().injectPopularFragment(this);
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false);
        binding.popularGrid.setLayoutManager(new GridLayoutManager(getActivity(), GRID_COLUMNS_COUNT));
        binding.popularGrid.setHasFixedSize(true);

        binding.popularGrid.setAdapter(gameGridAdapter);

        viewModel.fetchData(false);

        refreshGridSpan();

        return binding.getRoot();
    }

    private void refreshGridSpan() {
        ((GridLayoutManager) binding.popularGrid.getLayoutManager()).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return gameGridAdapter.getSpanSizeForGrid(position);
            }
        });
    }

}
