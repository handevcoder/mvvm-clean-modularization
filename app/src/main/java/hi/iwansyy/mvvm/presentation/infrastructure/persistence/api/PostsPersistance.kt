package hi.iwansyy.mvvm.presentation.infrastructure.persistence.api

import hi.iwansyy.mvvm.data.payload.api.posts.PostsRequest
import hi.iwansyy.mvvm.data.payload.api.posts.PostsResponse
import hi.iwansyy.mvvm.data.persistances.contracts.PostsPersistanceContract
import hi.iwansyy.mvvm.presentation.infrastructure.api.posts.service.PostsService

class PostsPersistance(private val service: PostsService): PostsPersistanceContract {
    override suspend fun getAllPosts(): List<PostsResponse> {
        return service.getAllPosts()
    }

    override suspend fun getPostsById(id: Int): PostsResponse {
        return service.getPostsById(id)
    }

    override suspend fun insertPosts(body: PostsRequest): PostsResponse {
        return service.insertPosts(body)
    }

    override suspend fun updatePosts(id: Int, body: PostsRequest): PostsResponse {
        return service.updatePostsById(id, body)
    }

    override suspend fun deletePosts(id: Int): PostsResponse {
        return service.deletePostsById(id)
    }
}