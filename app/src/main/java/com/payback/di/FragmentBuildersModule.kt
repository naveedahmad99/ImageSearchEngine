package com.payback.di

import com.payback.ui.photo.PhotoFragment
import com.payback.ui.search.SearchPhotoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributePhotoFragment(): PhotoFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchPhotoFragment(): SearchPhotoFragment
}