package lemond.annoying.gamerscompanion.main_activity.view;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import lemond.annoying.gamerscompanion.R;
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel;
import lemond.annoying.gamerscompanion.databinding.ActivityMainBinding;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.view.NewsFragment;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.view.NowFragment;
import lemond.annoying.gamerscompanion.main_activity.fragment_search.SearchFragment;

import static lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel.NEWS_TAB_INDEX;
import static lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel.NOW_TAB_INDEX;
import static lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel.SEARCH_TAB_INDEX;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector, BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String NOW_FRAGMENT_TAG = "now";
    private static final String NEWS_FRAGMENT_TAG = "news";
    private static final String SEARCH_FRAGMENT_TAG = "search";

    private ActivityMainBinding binding;

    @Inject
    protected MainActivityViewModel viewModel;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel.getSelectedPageLiveData().observe(this, this::switchFragment);

        binding.mainNavigationBottom.setOnNavigationItemSelectedListener(this);
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
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
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
