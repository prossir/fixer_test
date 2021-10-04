package paolo.rossi.fixertest

import com.jakewharton.threetenabp.AndroidThreeTen
import androidx.multidex.MultiDexApplication
import paolo.rossi.fixertest.di.injectAppModules
import timber.log.Timber


class App: MultiDexApplication() {

    override fun onCreate() {
        initAndroidThreeTen()
        initTimber()
        super.onCreate()
        injectModules()
    }

    private fun initAndroidThreeTen() {
        AndroidThreeTen.init(this)
    }

    private fun injectModules() {
        injectAppModules(this)
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

}