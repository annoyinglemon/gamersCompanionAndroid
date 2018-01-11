package lemond.annoying.gamerscompanion.fragment_news.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.activity.view.MainActivity;
import lemond.annoying.gamerscompanion.databinding.FragmentNewsBinding;
import lemond.annoying.gamerscompanion.fragment_news.injection.DaggerNewsComponent;
import lemond.annoying.gamerscompanion.fragment_news.injection.NewsComponent;
import lemond.annoying.gamerscompanion.fragment_news.injection.NewsModule;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsFragmentViewModel;


public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    @Inject
    protected NewsFragmentViewModel viewModel;

    @Inject
    protected NewsAdapter newsAdapter;

    public NewsFragment() {}

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getData().observe(this, dataState -> {
            newsAdapter.setCurrentDataWrapper(dataState);
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);

        NewsComponent component = DaggerNewsComponent.builder()
                .newsModule(new NewsModule(this))
                .mainActivityComponent(((MainActivity) getActivity()).getComponent())
                .build();

        component.inject(this);

        binding.newsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.newsList.setAdapter(newsAdapter);

        viewModel.fetchData(false);

        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
