package lemond.annoying.gamerscompanion.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import javax.inject.Inject;

import lemond.annoying.gamerscompanion.fragment_news.NewsFragment;
import lemond.annoying.gamerscompanion.fragment_now.NowFragment;
import lemond.annoying.gamerscompanion.fragment_search.SearchFragment;


public class MainFragmentPagerAdapter extends FragmentStatePagerAdapter {

    @Inject
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
