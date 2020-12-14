package hi.iwansyy.mvvm.presentation.infrastructure.api.posts.client

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