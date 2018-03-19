package lemond.annoying.gamerscompanion.main_activity.fragment_news.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
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

import dagger.android.support.AndroidSupportInjection;
import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsFragmentViewModelFactory;
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel;
import lemond.annoying.gamerscompanion.app.GlideApp;
import lemond.annoying.gamerscompanion.databinding.FragmentNewsBinding;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.viewmodel.NewsFragmentViewModel;


public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private FragmentNewsBinding binding;

    @Inject
    protected MainActivityViewModel mainActivityViewModel;

    @Inject
    protected NewsFragmentViewModelFactory newsFragmentViewModelFactory;

    private NewsFragmentViewModel viewModel;

    protected NewsAdapter newsAdapter;

    public NewsFragment() {
    }

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);

        viewModel = ViewModelProviders.of(this, newsFragmentViewModelFactory).get(NewsFragmentViewModel.class);

        binding.swipeRefreshNewsFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.swipeRefreshNewsFragment.setOnRefreshListener(this);

        newsAdapter = new NewsAdapter(GlideApp.with(this), getString(R.string.more_news));

        binding.swipeRefreshNewsFragment.setAdapter(newsAdapter);

        mainActivityViewModel.getSelectedPageLiveData().observe(this, this::resetSession);

        viewModel.fetchData(false);

        return binding.getRoot();
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
