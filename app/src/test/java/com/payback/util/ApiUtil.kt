package com.payback.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.payback.api.ApiResponse
import retrofit2.Response

object ApiUtil {

    fun <T : Any> createCall(response: Response<T>) = MutableLiveData<ApiResponse<T>>().apply {
        value = ApiResponse.create(response)
    } as LiveData<ApiResponse<T>>
}