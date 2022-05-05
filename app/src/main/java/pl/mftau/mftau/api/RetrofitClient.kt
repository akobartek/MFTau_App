package pl.mftau.mftau.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.mftau.mftau.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private var kapucyniApiClient: Retrofit? = null
    val kapucyniApi: MftauApi = getMftauApiClient().create(MftauApi::class.java)

    private fun getMftauApiClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.MFTAU_API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(getAuthorizedOkHttpClient())
            .build()
    }

    private fun getAuthorizedOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                var request = chain.request()
                request = request.newBuilder()
                    .addHeader("Authorization", BuildConfig.MFTAU_API_KEY)
                    .build()

                chain.proceed(request)
            }
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }
}