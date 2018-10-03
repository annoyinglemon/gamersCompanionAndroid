package lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_page.model.NowPageRepository

class NowPageViewModelFactory(private val nowPageRepository: NowPageRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NowPageFragmentViewModel::class.java)) {
            return NowPageFragmentViewModel(nowPageRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
