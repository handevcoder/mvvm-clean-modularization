package hi.iwansyy.mvvm.state

import hi.iwansyy.mvvm.model.PostsModel
import java.lang.Exception

sealed class StatePosts {
    data class Loading(val message: String = "Loading.... ") : StatePosts()
    data class Error(val exception: Exception) : StatePosts()
    data class getAllPostsSuccessfully(val list: List<PostsModel>) : StatePosts()
    data class SuccessfullyInsertPosts(val posts: PostsModel) : StatePosts()
    data class SuccessfullyUpdatePosts(val posts: PostsModel) : StatePosts()
    data class SuccessfullyDeletePosts(val id: Int) : StatePosts()
}