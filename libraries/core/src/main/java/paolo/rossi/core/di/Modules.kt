package paolo.rossi.core.di

import android.app.NotificationManager
import android.content.Context
import org.koin.core.module.Module
import org.koin.dsl.module
import paolo.rossi.core.db.di.databaseModule
import paolo.rossi.core.extensions.listByElementsOf
import paolo.rossi.core.network.di.networkModule


val coreModules by lazy {
    listByElementsOf<Module>(
        coreModule,
        networkModule,
        databaseModule,
    )
}

internal val coreModule = module {
    single { get<Context>().resources }
    single { get<Context>().resources.displayMetrics }
    single { get<Context>().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager }
}