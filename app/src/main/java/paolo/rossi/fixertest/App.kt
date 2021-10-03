package paolo.rossi.fixertest

import com.jakewharton.threetenabp.AndroidThreeTen
import androidx.multidex.MultiDexApplication
import paolo.rossi.fixertest.di.injectAppModules


class App: MultiDexApplication() {

    override fun onCreate() {
        initAndroidThreeTen()
        super.onCreate()
        injectModules()
    }

    private fun initAndroidThreeTen() {
        AndroidThreeTen.init(this)
    }

    private fun injectModules() {
        injectAppModules(this)
    }

}