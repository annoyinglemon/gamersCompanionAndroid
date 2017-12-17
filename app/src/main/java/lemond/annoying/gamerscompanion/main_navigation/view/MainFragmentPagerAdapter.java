package lemond.annoying.gamerscompanion.main_navigation.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import lemond.annoying.gamerscompanion.news.view.NewsFragment;
import lemond.annoying.gamerscompanion.now.NowFragment;
import lemond.annoying.gamerscompanion.search.view.SearchFragment;


public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 1:
                return NewsFragment.newInstance();
            case 2:
                return SearchFragment.newInstance();
            default:
            case 0:
                return NowFragment.newInstance();
        }
    }
}
