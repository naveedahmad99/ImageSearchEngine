package com.payback.repository

import com.payback.database.PhotoDoa
import com.payback.models.Photo
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(
    private val photoDao: PhotoDoa
) {

    fun loadPhotoObjectById(photoId: Int): Observable<Photo> {
        return Observable.create { emitter ->
            val photo = photoDao.getPhotoObjectById(photoId)
            emitter.onNext(photo)
        }
    }
}