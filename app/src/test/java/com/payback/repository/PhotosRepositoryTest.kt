package com.payback.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.payback.api.ApiResponse
import com.payback.api.PhotoSearchResponse
import com.payback.api.PixBayService
import com.payback.database.PhotoDoa
import com.payback.database.PixBayDatabase
import com.payback.models.Photo
import com.payback.models.PhotoSearchResult
import com.payback.models.Resource
import com.payback.util.InstantAppExecutors
import com.payback.util.TestUtil
import com.payback.util.mock
import com.payback.utils.Constants
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import retrofit2.Response

class PhotosRepositoryTest {
    private lateinit var repository: PhotosRepository
    private val dao = mock(PhotoDoa::class.java)
    private val service = mock(PixBayService::class.java)

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val db = mock(PixBayDatabase::class.java)
        `when`(db.photoDao()).thenReturn(dao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = PhotosRepository(InstantAppExecutors(), db, dao, service)
    }

    @Test
    fun searchPhotos() {

        val ids = arrayListOf(1, 2)
        val photo1 = TestUtil.createPhoto(1)
        val photo2 = TestUtil.createPhoto(2)

        val observer = mock<Observer<Resource<List<Photo>>>>()
        val dbSearchResult = MutableLiveData<PhotoSearchResult>()
        val photos = MutableLiveData<List<Photo>>()

        val photoList = arrayListOf(photo1, photo2)
        val apiResponse = PhotoSearchResponse(Math.random().toInt(), photoList, 2)

        // #1
        val callLiveData = MutableLiveData<ApiResponse<PhotoSearchResponse>>()
        `when`(service.searchPhotos(Constants.API_KEY, "friends", 1)).thenReturn(callLiveData)

        // #2
        `when`(dao.search("friends", 1)).thenReturn(dbSearchResult)


        repository.search("friends", 1).observeForever(observer)


        verify(observer).onChanged(Resource.loading(null))
        verifyNoMoreInteractions(service)
        reset(observer)

//        `when`(dao.loadOrdered(ids)).thenReturn(photos as LiveData<List<Photo>>)
//        Exception in this case WrongTypeOfReturnValue .. Solved by:
//        https://groups.google.com/forum/?fromgroups#!topic/mockito/9WUvkhZUy90

        doReturn(photos).`when`(dao).loadOrdered(ids)

        dbSearchResult.postValue(null)
        verify(dao, never()).loadOrdered(anyList())

        verify(service).searchPhotos(Constants.API_KEY, "friends", 1)
        val updatedResult = MutableLiveData<PhotoSearchResult>()
        `when`(dao.search("friends", 1)).thenReturn(updatedResult)
        updatedResult.postValue(PhotoSearchResult("friends", ids, 1))

        callLiveData.postValue(ApiResponse.create(Response.success(apiResponse)))
        verify(dao).insertPhotos(photoList)
        photos.postValue(photoList)
        verify(observer).onChanged(Resource.success(photoList))
        verifyNoMoreInteractions(service)

    }
}