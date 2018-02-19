package lemond.annoying.gamerscompanion.activity.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.activity.viewmodel.MainActivityViewModel;
import lemond.annoying.gamerscompanion.app.GamersApplication;
import lemond.annoying.gamerscompanion.app.ViewControllerComponent;
import lemond.annoying.gamerscompanion.app.module.MainActivityModule;
import lemond.annoying.gamerscompanion.databinding.ActivityMainBinding;
import lemond.annoying.gamerscompanion.fragment_news.view.NewsFragment;
import lemond.annoying.gamerscompanion.fragment_now.fragment_main.NowFragment;
import lemond.annoying.gamerscompanion.fragment_search.SearchFragment;

import static lemond.annoying.gamerscompanion.activity.viewmodel.MainActivityViewModel.NEWS_TAB_INDEX;
import static lemond.annoying.gamerscompanion.activity.viewmodel.MainActivityViewModel.NOW_TAB_INDEX;
import static lemond.annoying.gamerscompanion.activity.viewmodel.MainActivityViewModel.SEARCH_TAB_INDEX;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String NOW_FRAGMENT_TAG = "now";
    private static final String NEWS_FRAGMENT_TAG = "news";
    private static final String SEARCH_FRAGMENT_TAG = "search";

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private ViewControllerComponent viewControllerComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        viewControllerComponent = ((GamersApplication) getApplication()).getGamersAppComponent()
                .getViewControllerComponent(new MainActivityModule(this));

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        viewModel.getSelectedPageLiveData().observe(this, this::switchFragment);

        binding.mainNavigationBottom.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof NowFragment) {
            ((NowFragment) fragment).setViewControllerComponent(viewControllerComponent);
        } else if (fragment instanceof NewsFragment) {
            ((NewsFragment) fragment).setViewControllerComponent(viewControllerComponent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_news:
                viewModel.setSelectedPage(NEWS_TAB_INDEX);
                break;
            case R.id.main_menu_search:
                viewModel.setSelectedPage(SEARCH_TAB_INDEX);
                break;
            default:
            case R.id.main_menu_now:
                viewModel.setSelectedPage(NOW_TAB_INDEX);
                break;
        }
        return true;
    }

    private String getFragmentTagForTab(int position) {
        switch (position) {
            case NOW_TAB_INDEX:
            default:
                return NOW_FRAGMENT_TAG;
            case NEWS_TAB_INDEX:
                return NEWS_FRAGMENT_TAG;
            case SEARCH_TAB_INDEX:
                return SEARCH_FRAGMENT_TAG;
        }
    }

    private void switchFragment(int position) {
        if (isFinishing()) {
            return;
        }

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragmentsExceptFor(transaction, position);

        final String fragmentTag = getFragmentTagForTab(position);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentTag);
        if (fragment == null) {
            fragment = createFragment(position);
            transaction.add(R.id.main_fragment_container, fragment, fragmentTag);
        } else {
            transaction.show(fragment);
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideAllFragmentsExceptFor(FragmentTransaction transaction, int position) {
        for (int i = 0; i < binding.mainNavigationBottom.getMenu().size(); i++) {
            if (i != position) {
                final String fragmentTag = getFragmentTagForTab(i);

                final Fragment fragmentToHide = getSupportFragmentManager().findFragmentByTag(fragmentTag);
                if (fragmentToHide != null) {
                    transaction.hide(fragmentToHide);
                }
            }
        }
    }

    private Fragment createFragment(int position) {
        switch (position) {
            default:
            case NOW_TAB_INDEX:
                return NowFragment.newInstance();
            case NEWS_TAB_INDEX:
                return NewsFragment.newInstance();
            case SEARCH_TAB_INDEX:
                return SearchFragment.newInstance();
        }
    }
}
