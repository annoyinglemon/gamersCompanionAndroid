package lemond.annoying.gamerscompanion.fragment_now.fragment_page.view;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.app.GlideApp;
import lemond.annoying.gamerscompanion.fragment_now.adapter.GameGridAdapter;


public class PopularPageFragment extends NowPageFragment {

    public PopularPageFragment() {}

    public static PopularPageFragment newInstance() {
        return new PopularPageFragment();
    }

    @Override
    public void initializeGridAdapter() {
        gameGridAdapter = new GameGridAdapter(GlideApp.with(this), getString(R.string.more_popular));
    }
}
