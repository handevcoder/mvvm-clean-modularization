package hi.iwansyy.mvvm.repository

interface PostsRemoteRepository {
    suspend fun getAllPosts(): List<PostsResponse>
    suspend fun insertPosts(postsRequest: PostsRequest): PostsResponse
    suspend fun updatePostsById(id: Int, postsRequest: PostsRequest): PostsResponse
    suspend fun deletePostsById(id: Int): PostsResponse
}