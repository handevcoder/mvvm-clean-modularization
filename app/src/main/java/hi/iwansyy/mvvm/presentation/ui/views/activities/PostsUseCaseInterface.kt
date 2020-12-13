package hi.iwansyy.mvvm.presentation.ui.views.activities

import hi.iwansyy.mvvm.domain.PostsDomain

interface PostsUseCaseInterface {
    suspend fun getAllPosts(): List<PostsDomain>
    suspend fun getPostsById(id: Int): PostsDomain
    suspend fun insertPosts(body: PostsDomain): PostsDomain
    suspend fun updatePosts(id: Int, body: PostsDomain): PostsDomain
    suspend fun deletePosts(id: Int): PostsDomain

}