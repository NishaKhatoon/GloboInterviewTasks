package com.coder.globocominterviewapplication.apiCall.domain

import com.coder.globocominterviewapplication.apiCall.data.PostItem
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("posts")
    suspend fun getPosts(): List<PostItem>
}