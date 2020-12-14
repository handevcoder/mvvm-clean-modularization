package hi.iwansyy.mvvm.presentation.infrastructure.api.posts.service

import hi.iwansyy.data.payload.api.posts.PostsRequest
import hi.iwansyy.data.payload.api.posts.PostsResponse
import retrofit2.http.*

interface PostsService {
    @GET("posts")
    suspend fun getAllPosts(): List<PostsResponse>

    @POST("posts")
    suspend fun insertPosts(
            @Body postsRequest: PostsRequest
    ): PostsResponse

    @GET("posts/{id}")
    suspend fun getPostsById(
            @Path("id") id: Int
    ): PostsResponse

    @PUT("posts/{id}")
    suspend fun updatePostsById(
            @Path("id") id: Int,
            @Body postsRequest: PostsRequest
    ): PostsResponse

    @DELETE("posts/{id}")
    suspend fun deletePostsById(
            @Path("id") id: Int
    ): PostsResponse
}


/*
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
}*/
