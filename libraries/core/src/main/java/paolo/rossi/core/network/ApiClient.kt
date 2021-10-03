package paolo.rossi.core.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val CONTENT_TYPE_JSON = "application/json"

private const val CONNECTION_TIMEOUT_DEFAULT: Long = 5
private const val READ_TIMEOUT_DEFAULT: Long = 3
private const val WRITE_TIMEOUT_DEFAULT: Long = 5




fun createOkHttpClient(
    cache: Cache? = null,
    f: (Request.Builder.() -> Request.Builder)? = null
): OkHttpClient {
    return OkHttpClient.Builder()
        .cache(cache)
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            val request = (f?.invoke(requestBuilder) ?: requestBuilder)
            chain.proceed(request.build())
        }
        .addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .writeTimeout(WRITE_TIMEOUT_DEFAULT, TimeUnit.MINUTES)
        .readTimeout(READ_TIMEOUT_DEFAULT, TimeUnit.MINUTES)
        .connectTimeout(CONNECTION_TIMEOUT_DEFAULT, TimeUnit.MINUTES)
        .build()
}

fun createRetrofit(httpClient: OkHttpClient, baseUrl: String): Retrofit {
    val json = Json { allowStructuredMapKeys = true } /*val json = Json( configuration = JsonConfiguration.Stable ) */
    val contentType = CONTENT_TYPE_JSON.toMediaType()
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(httpClient)
        .build()
}
