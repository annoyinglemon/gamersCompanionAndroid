package lemond.annoying.gamerscompanion.main_activity.injection;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.injection.NewsFragmentComponent;
import lemond.annoying.gamerscompanion.main_activity.fragment_news.view.NewsFragment;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.fragment_main.view.NowFragment;
import lemond.annoying.gamerscompanion.main_activity.fragment_now.injection.NowFragmentComponent;


@Module
public abstract class FragmentBuilderModule {

    @Binds
    @IntoMap
    @FragmentKey(NowFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindNowFragment(NowFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(NewsFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindNewsFragment(NewsFragmentComponent.Builder builder);

}
