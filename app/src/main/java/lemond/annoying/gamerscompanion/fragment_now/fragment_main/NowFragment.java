package lemond.annoying.gamerscompanion.fragment_now.fragment_main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.activity.view.MainActivity;
import lemond.annoying.gamerscompanion.app.ViewControllerComponent;
import lemond.annoying.gamerscompanion.databinding.FragmentNowBinding;
import lemond.annoying.gamerscompanion.fragment_now.fragment_hyped.view.HypedFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.view.PopularFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.view.TrendingFragment;


public class NowFragment extends Fragment implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    private FragmentNowBinding binding;

    public NowFragment() {}

    public static NowFragment newInstance() {
        return new NowFragment();
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        ViewControllerComponent viewControllerComponent = ((MainActivity) getActivity()).getViewControllerComponent();
        if (childFragment instanceof HypedFragment) {
            viewControllerComponent.inject((HypedFragment) childFragment);
        } else if (childFragment instanceof PopularFragment) {
            viewControllerComponent.inject((PopularFragment) childFragment);
        } else if (childFragment instanceof TrendingFragment) {
            viewControllerComponent.inject((TrendingFragment) childFragment);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
