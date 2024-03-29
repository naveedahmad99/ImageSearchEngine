package com.payback.api


import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response


@RunWith(JUnit4::class)
class ApiResponseTest {

    @Test
    fun exception() {
        val exception = Exception("test")
        val apiErrorResponse = ApiResponse.create<String>(exception)
        val errorMessage = apiErrorResponse.errorMessage
//        val (errorMessage) = ApiResponse.create<String>(exception)
        assertThat<String>(errorMessage, `is`("test"))
    }

    @Test
    fun success() {
        val apiResponse = ApiResponse.create(Response.success("test")) as ApiSuccessResponse
        assertThat<String>(apiResponse.body, `is`("test"))
    }

    @Test
    fun error() {
        val errorResponse = Response.error<String>(
            400,
            "error".toResponseBody("application/txt".toMediaTypeOrNull())
        )
        val apiErrorResponse = ApiResponse.create<String>(errorResponse) as ApiErrorResponse
        val errorMessage = apiErrorResponse.errorMessage
//        val (errorMessage) = ApiResponse.create<String>(errorResponse) as ApiErrorResponse<String>
        assertThat<String>(errorMessage, `is`("error"))
    }
}