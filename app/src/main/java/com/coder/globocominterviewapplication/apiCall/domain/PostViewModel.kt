package com.coder.globocominterviewapplication.apiCall.domain

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coder.globocominterviewapplication.apiCall.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val mPostRepository: PostRepository): ViewModel()  {

    val response: MutableState<ApiState> = mutableStateOf(ApiState.Empty)

    init {
        getPost()
    }
    private fun getPost() =
        viewModelScope.launch {
            mPostRepository.getPost().onStart {
                response.value= ApiState.Loading
            }.catch {
                response.value= ApiState.Failure(it)
            }.collect {
                response.value=ApiState.Success(it)
            }
        }
}