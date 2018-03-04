package lemond.annoying.gamerscompanion.fragment_news.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.activity.viewmodel.MainActivityViewModel;
import lemond.annoying.gamerscompanion.app.GlideApp;
import lemond.annoying.gamerscompanion.databinding.FragmentNewsBinding;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsFragmentViewModel;


public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private FragmentNewsBinding binding;

    @Inject
    protected MainActivityViewModel mainActivityViewModel;

    @Inject
    protected NewsFragmentViewModel viewModel;

    protected NewsAdapter newsAdapter;

    public NewsFragment() {
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getLiveData().observe(this, listDataWrapper -> {
            if (listDataWrapper != null) {
                newsAdapter.setDataList(listDataWrapper.data);
                binding.swipeRefreshNewsFragment.setDisplayState(listDataWrapper.state);
                binding.swipeRefreshNewsFragment.setRefreshing(false);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);

        binding.swipeRefreshNewsFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.swipeRefreshNewsFragment.setOnRefreshListener(this);

        newsAdapter = new NewsAdapter(GlideApp.with(this), getString(R.string.more_news));

        binding.swipeRefreshNewsFragment.setAdapter(newsAdapter);

        mainActivityViewModel.getSelectedPageLiveData().observe(this, this::resetSession);

        viewModel.fetchData(false);

        return binding.getRoot();
    }

    private void resetSession(Integer pageSelected) {
        if (pageSelected != null && pageSelected == 1) {
            binding.swipeRefreshNewsFragment.scrollToTop(mainActivityViewModel.shouldAnimateScrollToTop());
        }
    }

    @Override
    public void onRefresh() {
        viewModel.fetchData(true);
    }
}
