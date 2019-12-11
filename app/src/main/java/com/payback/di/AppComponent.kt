package com.payback.di

import android.app.Application
import com.payback.PayBackApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class]
)
interface AppComponent {

    // Fields injection to inject dispatchingAndroidInjector in the BixBayApp
    fun inject(payBackApp: PayBackApp)

    @Component.Builder
    interface Builder {

        //  To pass an instance of Application at Runtime
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}