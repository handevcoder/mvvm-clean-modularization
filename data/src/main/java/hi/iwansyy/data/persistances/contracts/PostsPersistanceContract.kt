package hi.iwansyy.data.persistances.contracts

import hi.iwansyy.data.payload.api.posts.PostsRequest
import hi.iwansyy.data.payload.api.posts.PostsResponse

interface PostsPersistanceContract {
    suspend fun getAllPosts(): List<PostsResponse>
    suspend fun getPostsById(id: Int): PostsResponse
    suspend fun insertPosts(body: PostsRequest): PostsResponse
    suspend fun updatePosts(id: Int, body: PostsRequest): PostsResponse
    suspend fun deletePosts(id: Int): PostsResponse
}