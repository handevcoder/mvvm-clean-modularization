package hi.iwansyy.mvvm.model

import hi.iwansyy.mvvm.repository.PostsRequest
import hi.iwansyy.mvvm.repository.PostsResponse
import retrofit2.http.*

interface PostsService {
    @GET("posts")
    suspend fun getAllPosts(): List<PostsResponse>

    @POST("posts")
    suspend fun insertPosts(@Body postsRequest: PostsRequest): PostsResponse

    @PUT("posts/{id}")
    suspend fun updatePostsById(
        @Path("id") id: Int,
        @Body postsRequest: PostsRequest
    ): PostsResponse

    @DELETE("posts/{id}")
    suspend fun deletePostsById(@Path("id") id: Int): PostsResponse
}