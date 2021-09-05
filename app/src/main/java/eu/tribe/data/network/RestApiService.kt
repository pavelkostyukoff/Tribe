package eu.tribe.data.network
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestApiService {

    @GET("{section}.json")
    fun getNowWeather(@Path("section") id: String? = "", @Query("api-key") key: String): Deferred <News>

    companion object {
        private val LOG = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        fun createService(): RestApiService {

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(LOG)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/topstories/v2/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build().create(RestApiService::class.java)
        }
    }
}