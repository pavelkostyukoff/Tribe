package eu.tribe.Presentation.SingleActivity

import android.app.Application
import eu.tribe.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class TribeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
        startKoin {
            androidContext(this@TribeApplication)
            appDeclaration()
            modules(appModule)
        }
}