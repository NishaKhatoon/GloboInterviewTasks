package com.coder.globocominterviewapplication.apiCall.utils

import com.coder.globocominterviewapplication.apiCall.data.PostItem

sealed class ApiState {

    class Success(val data: List<PostItem>) : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    data object Loading:ApiState()
    data object Empty: ApiState()
}