package hi.iwansyy.mvvm.di

import com.google.gson.GsonBuilder
import hi.iwansyy.mvvm.BuildConfig.DEBUG
import hi.iwansyy.mvvm.data.persistances.contracts.PostsPersistanceContract
import hi.iwansyy.mvvm.data.persistances.mappers.PostsMapper
import hi.iwansyy.mvvm.data.persistances.repositories.PostsRepository
import hi.iwansyy.mvvm.data.persistances.repositories.PostsRepositoryInterface
import hi.iwansyy.mvvm.presentation.infrastructure.api.posts.service.PostsService
import hi.iwansyy.mvvm.presentation.ui.viewmodels.PostViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModule = module {
    fun providePostService(retrofit: Retrofit): PostsService {
        return retrofit.create(PostsService::class.java)
    }
    single { providePostService(get()) }
}
val networkModule = module {

    val gson by lazy { GsonBuilder().setLenient().create() }

    fun provideClient(): OkHttpClient{
        val loggingInterceptor by lazy { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }

        return OkHttpClient.Builder()
                    .apply { if (DEBUG) addInterceptor(loggingInterceptor) }
                    .build()
        }


    fun provideRetrofit(client:OkHttpClient, BASE_URL: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
    }

    single { provideClient() }

    single {
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        provideRetrofit(get(), BASE_URL)
    }
}

val repositoryModule = module {
    fun providePostsMapper(mapper: PostsMapper): PostsMapper {
        return mapper
    }

    fun providePostsRepositoryModule(api: PostsPersistanceContract, mapper: PostsMapper): PostsRepositoryInterface {
        return PostsRepository(api, mapper)
    }
    single { providePostsMapper(get()) }
    single { providePostsRepositoryModule(get(), providePostsMapper(get())) }
}



val viewModelModule = module {
    viewModel{
        PostViewModel(useCase = get())
    }
}
