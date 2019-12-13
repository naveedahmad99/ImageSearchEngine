package com.payback.ui.photo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.payback.models.Photo
import com.payback.repository.PhotoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhotoViewModel @Inject constructor(private val photoRepository: PhotoRepository) :
    ViewModel() {

    private val _photoId = MutableLiveData<Int>()
    var photo: MutableLiveData<Photo> = MutableLiveData()

    private fun loadPhotoById(id: Int) {
        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            photoRepository.loadPhotoObjectById(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableObserver<Photo>() {
                    override fun onNext(promosList: Photo) {
                        photo.value = promosList
                        compositeDisposable.clear()
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onComplete() {
                    }
                })
        )
    }

    fun setId(photoId: Int) {
        _photoId.value = photoId
        loadPhotoById(photoId)
    }

}