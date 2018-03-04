package lemond.annoying.gamerscompanion.fragment_now.fragment_main.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.activity.viewmodel.MainActivityViewModel;
import lemond.annoying.gamerscompanion.databinding.FragmentNowBinding;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.qualifier.HypedPage;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.qualifier.PopularPage;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.qualifier.TrendingPage;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.view.HypedPageFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.view.PopularPageFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.view.TrendingPageFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.viewmodel.NowPageFragmentViewModel;


public class NowFragment extends Fragment implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    private FragmentNowBinding binding;

    @Inject
    protected MainActivityViewModel mainActivityViewModel;

    @Inject
    @TrendingPage
    protected NowPageFragmentViewModel trendingViewModel;

    @Inject
    @PopularPage
    protected NowPageFragmentViewModel popularViewModel;

    @Inject
    @HypedPage
    protected NowPageFragmentViewModel hypedViewModel;

    public NowFragment() {}

    public static NowFragment newInstance() {
        return new NowFragment();
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
        if (childFragment instanceof TrendingPageFragment) {
            ((TrendingPageFragment) childFragment).setViewModels(mainActivityViewModel, trendingViewModel);
        } else if (childFragment instanceof PopularPageFragment) {
            ((PopularPageFragment) childFragment).setViewModels(mainActivityViewModel, popularViewModel);
        } else {
            ((HypedPageFragment) childFragment).setViewModels(mainActivityViewModel, hypedViewModel);
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
