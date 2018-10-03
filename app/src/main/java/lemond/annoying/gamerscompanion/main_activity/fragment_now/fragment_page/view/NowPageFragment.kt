package lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.view

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import lemond.annoying.gamerscompanion.R
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel.NowPageFragmentViewModel
import lemond.annoying.gamerscompanion.main_activity.viewmodel.MainActivityViewModel
import lemond.annoying.gamerscompanion.databinding.FragmentNowPageBinding
import lemond.annoying.gamerscompanion.main_activity.fragment_now.adapter.GameGridAdapter
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.viewmodel.GameItemViewModel


abstract class NowPageFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var viewModel: NowPageFragmentViewModel
    protected lateinit var gameGridAdapter: GameGridAdapter
    private lateinit var binding: FragmentNowPageBinding

    protected var mainActivityViewModel: MainActivityViewModel? = null

    fun setViewModels(mainActivityViewModel: MainActivityViewModel, viewModel: NowPageFragmentViewModel) {
        this.viewModel = viewModel
        this.viewModel.getLiveData().observe(this, Observer { listDataWrapper ->
                gameGridAdapter.setDataList(listDataWrapper?.data as? Collection<GameItemViewModel>)
                binding.swipeRefreshNowPageFragment.setDisplayState(listDataWrapper?.state)
                binding.swipeRefreshNowPageFragment.isRefreshing = false
                refreshGridSpan()
        })
        this.viewModel.fetchData(false)

        this.mainActivityViewModel = mainActivityViewModel

        this.mainActivityViewModel!!.selectedPageLiveData.observe(this, Observer<Int> { this.resetSession(it) })
    }

    private fun resetSession(pageSelected: Int?) {
        if (pageSelected != null && pageSelected == 0 && isVisible) {
            binding.swipeRefreshNowPageFragment.scrollToTop(mainActivityViewModel!!.shouldAnimateScrollToTop())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_now_page, container, false)

        binding.swipeRefreshNowPageFragment.layoutManager = GridLayoutManager(activity, GRID_COLUMNS_COUNT)
        binding.swipeRefreshNowPageFragment.setOnRefreshListener(this)

        initializeGridAdapter()

        binding.swipeRefreshNowPageFragment.setAdapter(gameGridAdapter)

        refreshGridSpan()

        return binding.root
    }

    private fun refreshGridSpan() {
        (binding.swipeRefreshNowPageFragment.layoutManager as GridLayoutManager).spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return gameGridAdapter.getSpanSizeForGrid(position)
            }
        }
    }

    override fun onRefresh() {
        viewModel.fetchData(true)
    }

    abstract fun initializeGridAdapter()

    companion object {
        private val GRID_COLUMNS_COUNT = 2
    }

}