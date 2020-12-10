package hi.iwansyy.mvvm.di

import com.google.gson.GsonBuilder
import hi.iwansyy.mvvm.model.PostsService
import hi.iwansyy.mvvm.repository.PostsRemoteRepository
import hi.iwansyy.mvvm.repository.PostsRemoteViewRepositoryImpl
import hi.iwansyy.mvvm.viewmodel.PostViewModel
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
    fun provideRetrofit(BASE_URL: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .build()
    }
    single {
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        provideRetrofit(get())
    }
}
val repositoryModule = module {
    fun providePostsRepositoryModule(api: PostsService): PostsRemoteRepository {
        return PostsRemoteViewRepositoryImpl(api)
    }
    single { providePostsRepositoryModule(get()) }
}
val viewModelModule = module {
    viewModel{
        PostViewModel(postsRemoteRepository = get ())
    }
}
