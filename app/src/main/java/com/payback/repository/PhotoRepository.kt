package com.payback.repository

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.payback.database.PhotoDoa
import com.payback.models.Photo
import io.reactivex.disposables.CompositeDisposable
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoRepository @Inject constructor(
    private val photoDao: PhotoDoa
) {
    fun loadPhotoById(photoId: Int): LiveData<Photo> {
        return photoDao.getPhotoById(photoId)
    }
}