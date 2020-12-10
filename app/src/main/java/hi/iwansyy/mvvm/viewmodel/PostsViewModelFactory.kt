package hi.iwansyy.mvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hi.iwansyy.mvvm.repository.PostsRemoteRepository

@Suppress("UNCHECKED_CAST")
class PostsViewModelFactory(private val remoteRepository: PostsRemoteRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostViewModel(remoteRepository) as T
    }

}