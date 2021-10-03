package paolo.rossi.core.network.di

import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import paolo.rossi.core.BuildConfig
import paolo.rossi.core.network.createOkHttpClient
import paolo.rossi.core.network.createRetrofit
import retrofit2.Retrofit


const val NAMED_API_BASE = "BASE"
const val NAMED_API_BASE_CLIENT = "BASE_CLIENT"

internal val networkModule = module {
    single(named(NAMED_API_BASE_CLIENT)) { createOkHttpClient() }
    single(named(NAMED_API_BASE)) { retrofitBase(get(named(NAMED_API_BASE_CLIENT))) }

}

private fun retrofitBase(okHttpClient: OkHttpClient): Retrofit {
    return createRetrofit(okHttpClient, BuildConfig.BASE_URL)
}
