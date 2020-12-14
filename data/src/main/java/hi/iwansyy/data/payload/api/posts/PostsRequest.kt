package hi.iwansyy.data.payload.api.posts

import com.google.gson.annotations.SerializedName

data class PostsRequest(
        @field:SerializedName("title")
        val title: String?,
        @field:SerializedName("body")
        val body: String?
)
