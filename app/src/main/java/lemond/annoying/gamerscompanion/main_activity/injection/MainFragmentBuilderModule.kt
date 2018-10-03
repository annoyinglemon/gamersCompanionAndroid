package lemond.annoying.gamerscompanion.main_activity.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import lemond.annoying.gamerscompanion.main_activity.fragment_news.injection.NewsFragmentScope
import lemond.annoying.gamerscompanion.main_activity.fragment_news.view.NewsFragment
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.view.NowFragment
import lemond.annoying.gamerscompanion.main_activity.fragment_now.injection.NowFragmentModule
import lemond.annoying.gamerscompanion.main_activity.fragment_now.injection.NowFragmentScope


@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector(modules = [NowFragmentModule::class])
    @NowFragmentScope
    internal abstract fun bindNowFragment(): NowFragment

    @ContributesAndroidInjector
    @NewsFragmentScope
    internal abstract fun bindNewsFragment(): NewsFragment

}
