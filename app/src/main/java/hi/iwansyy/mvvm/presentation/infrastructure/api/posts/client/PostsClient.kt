package hi.iwansyy.mvvm.presentation.infrastructure.api.posts.client

import com.google.gson.GsonBuilder
import hi.iwansyy.mvvm.BuildConfig.DEBUG
import hi.iwansyy.mvvm.presentation.infrastructure.api.posts.service.PostsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsClient {
    /*companion object{
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        private val gson by lazy {
         GsonBuilder()
         .setLenient().create()
         }
        private val loggingInterceptor by lazy {
            HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }
        }
        private val client by lazy {
            OkHttpClient.Builder()
                    .apply { if (DEBUG) addInterceptor(loggingInterceptor) }
                    .build()
        }
        private val retrofit by lazy {
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build()
        }
        val service by lazy { retrofit.create(PostsService::class.java) }
    }*/
}