package hi.iwansyy.mvvm.presentation.ui.states

import hi.iwansyy.domain.PostsDomain
import java.lang.Exception

sealed class StatePosts {
    data class Loading(val message: String = "Loading.... ") : StatePosts()
    data class Error(val exception: Exception) : StatePosts()
    data class getAllPostsSuccessfully(val list: List<hi.iwansyy.domain.PostsDomain>) : StatePosts()
    data class getPostsDetailSuccessfully(val posts: hi.iwansyy.domain.PostsDomain) : StatePosts()
    data class SuccessfullyInsertPosts(val posts: hi.iwansyy.domain.PostsDomain) : StatePosts()
    data class SuccessfullyUpdatePosts(val posts: hi.iwansyy.domain.PostsDomain) : StatePosts()
    data class SuccessfullyDeletePosts(val posts: hi.iwansyy.domain.PostsDomain) : StatePosts()
}