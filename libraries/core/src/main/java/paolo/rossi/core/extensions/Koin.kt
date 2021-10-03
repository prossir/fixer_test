package paolo.rossi.core.extensions

import android.app.Activity
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

inline fun <reified T : Any> Activity.inject(vararg params: Any?): Lazy<T> =
    inject(parameters = { parametersOf(*params) })

inline fun <reified T : Any> Fragment.inject(vararg params: Any?): Lazy<T> =
    inject(parameters = { parametersOf(*params) })

inline fun <reified T : Any> Activity.get(vararg params: Any?): T =
    get(parameters = { parametersOf(*params) })

inline fun <reified T : Any> Fragment.get(vararg params: Any?): T =
    get(parameters = { parametersOf(*params) })
