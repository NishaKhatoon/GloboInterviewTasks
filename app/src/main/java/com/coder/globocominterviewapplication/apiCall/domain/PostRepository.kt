package com.coder.globocominterviewapplication.apiCall.domain

import com.coder.globocominterviewapplication.apiCall.data.PostItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PostRepository @Inject constructor(private val apiService: ApiService){

    fun getPost(): Flow<List<PostItem>> = flow {
        emit(apiService.getPosts())
    }.flowOn(Dispatchers.IO)
}