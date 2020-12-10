package hi.iwansyy.mvvm.repository

import hi.iwansyy.mvvm.model.PostsService

class PostsRemoteViewRepositoryImpl(private val service: PostsService) : PostsRemoteRepository {
    override suspend fun getAllPosts(): List<PostsResponse> {
        return service.getAllPosts()
    }

    override suspend fun insertPosts(postsRequest: PostsRequest): PostsResponse {
        return service.insertPosts(postsRequest)
    }

    override suspend fun updatePostsById(id: Int, postsRequest: PostsRequest): PostsResponse {
        return service.updatePostsById(id, postsRequest)
    }

    override suspend fun deletePostsById(id: Int): PostsResponse {
        return service.deletePostsById(id)
    }
}