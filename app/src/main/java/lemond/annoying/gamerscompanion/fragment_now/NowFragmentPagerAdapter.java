package lemond.annoying.gamerscompanion.fragment_now;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lemond.annoying.gamerscompanion.fragment_now.fragment_popular.view.PopularFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_trending.view.TrendingFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_released.ReleasedFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_soon.SoonFragment;

public class NowFragmentPagerAdapter extends FragmentStatePagerAdapter {

    public NowFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        // TODO: 2017-12-17 use resource string
        switch (position) {
            case 1:
                return "Popular";
            case 2:
                return "Released";
            case 3:
                return "Soon";
            default:
            case 0:
                return "Trending";
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return PopularFragment.newInstance();
            case 2:
                return ReleasedFragment.newInstance();
            case 3:
                return SoonFragment.newInstance();
            default:
            case 0:
                return TrendingFragment.newInstance();
        }
    }
}
