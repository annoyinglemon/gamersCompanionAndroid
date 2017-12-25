package lemond.annoying.gamerscompanion.fragment_now.fragment_popular;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.databinding.FragmentPopularBinding;
import lemond.annoying.gamerscompanion.activity.MainActivity;



public class PopularFragment extends Fragment {

    private static final int GRID_COLUMNS_COUNT = 2;

    @Inject
    PopularViewModel viewModel;

    @Inject
    PopularAdapter popularAdapter;


    public PopularFragment() {}

    public static PopularFragment newInstance() {
        return new PopularFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("kurt", "viewModel: " + viewModel);
        viewModel.getMostPopularGames().observe(this, games -> {
            popularAdapter.setPopularGamesList(games);
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentPopularBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_popular, container, false);
        binding.popularGrid.setLayoutManager(new GridLayoutManager(getActivity(), GRID_COLUMNS_COUNT));
        binding.popularGrid.setHasFixedSize(true);

        PopularFragmentComponent component = DaggerPopularFragmentComponent.builder()
                .popularFragmentModule(new PopularFragmentModule(this))
                .mainActivityComponent(((MainActivity) getActivity()).getComponent())
                .build();

        component.injectPopularFragment(this);

        binding.popularGrid.setAdapter(popularAdapter);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
