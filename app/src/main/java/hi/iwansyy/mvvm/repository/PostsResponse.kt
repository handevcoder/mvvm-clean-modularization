package hi.iwansyy.mvvm.repository

import com.google.gson.annotations.SerializedName
import hi.iwansyy.mvvm.model.PostsModel

data class PostsResponse(
    @field:SerializedName("id") val id: Int,
    @field:SerializedName("title") val title: String,
    @field:SerializedName("body") val body: String,
    @field:SerializedName("userId") val userId: Int
)

fun PostsResponse.toModel() = PostsModel(id, title, body, userId)