package hi.iwansyy.mvvm

import android.app.Application
import hi.iwansyy.mvvm.di.apiModule
import hi.iwansyy.mvvm.di.networkModule
import hi.iwansyy.mvvm.di.repositoryModule
import hi.iwansyy.mvvm.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PostApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PostApp)
            modules(
                    apiModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule
            )
        }
    }
}