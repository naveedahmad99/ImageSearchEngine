package com.payback.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.payback.ui.photo.PhotoViewModel
import com.payback.ui.search.SearchPhotoViewModel
import com.payback.viewmodels.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchPhotoViewModel::class)
    abstract fun bindSearchPhotoViewModel(searchPhotoViewModel: SearchPhotoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PhotoViewModel::class)
    abstract fun bindPhotoViewModel(photoViewModel: PhotoViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}