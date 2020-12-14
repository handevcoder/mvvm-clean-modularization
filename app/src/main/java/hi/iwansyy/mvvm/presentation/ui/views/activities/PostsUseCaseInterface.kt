package hi.iwansyy.mvvm.presentation.ui.views.activities

import hi.iwansyy.domain.PostsDomain

interface PostsUseCaseInterface {
    suspend fun getAllPosts(): List<hi.iwansyy.domain.PostsDomain>
    suspend fun getPostsById(id: Int): hi.iwansyy.domain.PostsDomain
    suspend fun insertPosts(body: hi.iwansyy.domain.PostsDomain): hi.iwansyy.domain.PostsDomain
    suspend fun updatePosts(id: Int, body: hi.iwansyy.domain.PostsDomain): hi.iwansyy.domain.PostsDomain
    suspend fun deletePosts(id: Int): hi.iwansyy.domain.PostsDomain

}