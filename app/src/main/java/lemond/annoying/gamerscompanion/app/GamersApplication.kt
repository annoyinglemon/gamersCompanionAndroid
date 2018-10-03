package lemond.annoying.gamerscompanion.app

import android.app.Activity
import android.support.multidex.MultiDexApplication

import javax.inject.Inject

import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber


class GamersApplication : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        DaggerGamersAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return activityDispatchingAndroidInjector
    }

}
