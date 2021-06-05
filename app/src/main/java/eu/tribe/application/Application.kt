package eu.tribe.application

import android.app.Application
import eu.tribe.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import timber.log.Timber

class Application : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

        startKoin {
            androidLogger()
            androidContext(this@Application)
            koin.loadModules(listOf(module))
            koin.createRootScope()
        }
    }
}