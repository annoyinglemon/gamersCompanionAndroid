package lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.model.NowPageRepository;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.HypedPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.view.PopularPageFragment;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageFragmentViewModel;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageViewModelFactory;
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel;
import lemond.annoying.gamerscompanion.databinding.FragmentNowBinding;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.PopularPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.qualifier.TrendingPage;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.view.HypedPageFragment;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.view.TrendingPageFragment;


public class NowFragment extends Fragment implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    private FragmentNowBinding binding;

    @Inject
    protected MainActivityViewModel mainActivityViewModel;

    @Inject
    @TrendingPage
    protected NowPageViewModelFactory trendingViewModelFactory;

    @Inject
    @PopularPage
    protected NowPageViewModelFactory popularViewModelFactory;

    @Inject
    @HypedPage
    protected NowPageViewModelFactory hypedViewModelFactory;

    public NowFragment() {}

    public static NowFragment newInstance() {
        return new NowFragment();
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_now, container, false);

        NowFragmentPagerAdapter mainFragmentPagerAdapter = new NowFragmentPagerAdapter(getChildFragmentManager());

        binding.viewpagerNowFragment.setAdapter(mainFragmentPagerAdapter);

        binding.tablayoutNowFragment.addOnTabSelectedListener(this);
        binding.viewpagerNowFragment.addOnPageChangeListener(this);

        binding.tablayoutNowFragment.setupWithViewPager(binding.viewpagerNowFragment);

        return binding.getRoot();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Log.d("kurt", "NowFragment onAttachFragment is called");
        if (childFragment instanceof TrendingPageFragment) {
            NowPageFragmentViewModel viewModel = ViewModelProviders.of(this, trendingViewModelFactory)
                    .get(NowPageRepository.PageType.TRENDING.toString(), NowPageFragmentViewModel.class);
            ((TrendingPageFragment) childFragment).setViewModels(mainActivityViewModel, viewModel);

        } else if (childFragment instanceof PopularPageFragment) {
            NowPageFragmentViewModel viewModel = ViewModelProviders.of(this, popularViewModelFactory)
                    .get(NowPageRepository.PageType.POPULAR.toString(), NowPageFragmentViewModel.class);
            ((PopularPageFragment) childFragment).setViewModels(mainActivityViewModel, viewModel);

        } else {
            NowPageFragmentViewModel viewModel = ViewModelProviders.of(this, hypedViewModelFactory)
                    .get(NowPageRepository.PageType.HYPED.toString(), NowPageFragmentViewModel.class);
            ((HypedPageFragment) childFragment).setViewModels(mainActivityViewModel, viewModel);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        binding.viewpagerNowFragment.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {}

    @Override
    public void onTabReselected(TabLayout.Tab tab) {}

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        TabLayout.Tab tab = binding.tablayoutNowFragment.getTabAt(position);
        if (tab != null) {
            tab.select();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
