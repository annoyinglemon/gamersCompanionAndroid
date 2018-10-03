package lemond.annoying.gamerscompanion.main_activity.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.os.Bundle
import android.view.MenuItem

import javax.inject.Inject

import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel
import lemond.annoying.gamerscompanion.databinding.ActivityMainBinding
import lemond.annoying.gamerscompanion.main_activity.fragment_news.view.NewsFragment
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.view.NowFragment
import lemond.annoying.gamerscompanion.main_activity.fragment_search.SearchFragment
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel.Companion.NEWS_TAB_INDEX
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel.Companion.NOW_TAB_INDEX
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel.Companion.SEARCH_TAB_INDEX

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainActivityViewModel

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)

        viewModel.selectedPageLiveData.observe(this, Observer<Int> { this.switchFragment(it!!) })

        if (viewModel.selectedPageLiveData.value != null) {
            switchFragment(viewModel.selectedPageLiveData.value!!)
        }

        binding.mainNavigationBottom.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_menu_news -> viewModel.setSelectedPage(NEWS_TAB_INDEX)
            R.id.main_menu_search -> viewModel.setSelectedPage(SEARCH_TAB_INDEX)
            R.id.main_menu_now -> viewModel.setSelectedPage(NOW_TAB_INDEX)
            else -> viewModel.setSelectedPage(NOW_TAB_INDEX)
        }
        return true
    }

    private fun getFragmentTagForTab(position: Int): String {
        when (position) {
            NOW_TAB_INDEX -> return NOW_FRAGMENT_TAG
            NEWS_TAB_INDEX -> return NEWS_FRAGMENT_TAG
            SEARCH_TAB_INDEX -> return SEARCH_FRAGMENT_TAG
            else -> return NOW_FRAGMENT_TAG
        }
    }

    private fun switchFragment(position: Int) {
        if (isFinishing) {
            return
        }

        val transaction = supportFragmentManager.beginTransaction()
        hideAllFragmentsExceptFor(transaction, position)

        val fragmentTag = getFragmentTagForTab(position)

        var fragment = supportFragmentManager.findFragmentByTag(fragmentTag)
        if (fragment == null) {
            fragment = createFragment(position)
            transaction.add(R.id.main_fragment_container, fragment, fragmentTag)
        } else {
            transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
            transaction.show(fragment)
        }
        transaction.commitAllowingStateLoss()
    }

    private fun hideAllFragmentsExceptFor(transaction: FragmentTransaction, position: Int) {
        for (i in 0 until binding.mainNavigationBottom.menu.size()) {
            if (i != position) {
                val fragmentTag = getFragmentTagForTab(i)

                val fragmentToHide = supportFragmentManager.findFragmentByTag(fragmentTag)
                if (fragmentToHide != null) {
                    transaction.hide(fragmentToHide)
                }
            }
        }
    }

    private fun createFragment(position: Int): Fragment {
        when (position) {
            NOW_TAB_INDEX -> return NowFragment.newInstance()
            NEWS_TAB_INDEX -> return NewsFragment.newInstance()
            SEARCH_TAB_INDEX -> return SearchFragment.newInstance()
            else -> return NowFragment.newInstance()
        }
    }

    companion object {

        private val NOW_FRAGMENT_TAG = "now"
        private val NEWS_FRAGMENT_TAG = "news"
        private val SEARCH_FRAGMENT_TAG = "search"
    }
}
