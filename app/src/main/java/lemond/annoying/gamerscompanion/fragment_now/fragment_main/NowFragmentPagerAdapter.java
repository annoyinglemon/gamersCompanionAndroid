package lemond.annoying.gamerscompanion.fragment_now.fragment_main;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lemond.annoying.gamerscompanion.fragment_now.fragment_page.view.HypedPageFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.view.PopularPageFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_page.view.TrendingPageFragment;

public class NowFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private static final int FRAGMENT_TRENDING_POSITION = 0;
    private static final int FRAGMENT_POPULAR_POSITION = 1;
    private static final int FRAGMENT_HYPED_POSITION = 2;

    private static final String TAB_TEXT_TRENDING = "trending";
    private static final String TAB_TEXT_POPULAR = "popular";
    private static final String TAB_TEXT_HYPED = "hyped";

    NowFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case FRAGMENT_POPULAR_POSITION:
                return TAB_TEXT_POPULAR;
            case FRAGMENT_HYPED_POSITION:
                return TAB_TEXT_HYPED;
            default:
            case FRAGMENT_TRENDING_POSITION:
                return TAB_TEXT_TRENDING;
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case FRAGMENT_POPULAR_POSITION:
                return PopularPageFragment.newInstance();
            case FRAGMENT_HYPED_POSITION:
                return HypedPageFragment.newInstance();
            default:
            case FRAGMENT_TRENDING_POSITION:
                return TrendingPageFragment.newInstance();
        }
    }
}
