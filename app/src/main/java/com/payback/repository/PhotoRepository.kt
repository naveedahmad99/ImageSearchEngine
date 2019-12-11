package com.payback.repository

import androidx.lifecycle.LiveData
import com.payback.database.PhotoDoa
import com.payback.models.Photo
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