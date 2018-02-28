package lemond.annoying.gamerscompanion.fragment_now.fragment_page.view;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.app.GlideApp;
import lemond.annoying.gamerscompanion.fragment_now.adapter.GameGridAdapter;


public class HypedPageFragment extends NowPageFragment {

    public HypedPageFragment() {}

    public static HypedPageFragment newInstance() {
        return new HypedPageFragment();
    }

    @Override
    public void initializeGridAdapter() {
        gameGridAdapter = new GameGridAdapter(GlideApp.with(this), getString(R.string.more_hyped));
    }

}
