package com.coder.globocominterviewapplication.apiCall.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.coder.globocominterviewapplication.apiCall.data.PostItem
import com.coder.globocominterviewapplication.apiCall.domain.PostViewModel
import com.coder.globocominterviewapplication.apiCall.utils.ApiState
import com.coder.globocominterviewapplication.ui.theme.card_stroke_color
import com.coder.globocominterviewapplication.ui.theme.white


@Composable
fun GETData(mPostViewModel: PostViewModel) {

    when (val result = mPostViewModel.response.value) {
        is ApiState.Success -> {
            LazyColumn {
                items(result.data) { response ->
                    EachRow(post = response)
                }
            }
        }

        is ApiState.Failure -> {
            Text(text = "${result.msg}")
        }

        ApiState.Loading -> {
            CircularProgressIndicator()
        }

        ApiState.Empty -> {

        }
    }

}

@Composable fun EachRow(post: PostItem) {
    Card(
        shape = RoundedCornerShape(50.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 8.dp,
                vertical = 8.dp),
        border = BorderStroke(1.dp, card_stroke_color), // Border stroke width and color
        colors = CardDefaults.cardColors(
            containerColor = white,
        )
    ) {
        Text(
            text = post.body!!,
            modifier = Modifier.padding(10.dp)
        )
    }

}