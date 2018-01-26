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
import lemond.annoying.gamerscompanion.app.GlideApp;
import lemond.annoying.gamerscompanion.databinding.FragmentNewsBinding;
import lemond.annoying.gamerscompanion.fragment_news.viewmodel.NewsFragmentViewModel;


public class NewsFragment extends Fragment {

    private FragmentNewsBinding binding;

    @Inject
    protected NewsFragmentViewModel viewModel;

    protected NewsAdapter newsAdapter;

    public NewsFragment() {}

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getData().observe(this, newsAdapter::setCurrentDataWrapper);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() != null) {
            ((MainActivity) getActivity()).getViewControllerComponent().inject(this);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);

        binding.newsList.setLayoutManager(new LinearLayoutManager(getActivity()));

        newsAdapter = new NewsAdapter(GlideApp.with(this));

        binding.newsList.setAdapter(newsAdapter);

        viewModel.fetchData(false);

        return binding.getRoot();
    }

}
