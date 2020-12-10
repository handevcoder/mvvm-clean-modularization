package hi.iwansyy.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hi.iwansyy.mvvm.model.PostsModel
import hi.iwansyy.mvvm.model.toRequest
import hi.iwansyy.mvvm.repository.PostsRemoteRepository
import hi.iwansyy.mvvm.repository.toModel
import hi.iwansyy.mvvm.state.StatePosts
import hi.iwansyy.mvvm.state.StatePosts.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(private val postsRemoteRepository: PostsRemoteRepository) : ViewModel() {
    private val mutableState by lazy { MutableLiveData<StatePosts>() }
    val state: LiveData<StatePosts> get() = mutableState

    fun getAllPosts() {
        mutableState.value = Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val postsResponse = postsRemoteRepository.getAllPosts()
                val postsList = postsResponse.asSequence().map { it.toModel() }.toList()
                mutableState.postValue(getAllPostsSuccessfully(postsList))
            } catch (ex: Exception) {
                ex.printStackTrace()
                mutableState.postValue(Error(ex))
            }
        }
    }

    fun insertPosts(postsModel: PostsModel) {
        mutableState.value = Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val postsResponse = postsRemoteRepository.insertPosts(postsModel.toRequest())
                val posts = postsResponse.toModel()
                mutableState.postValue(SuccessfullyInsertPosts(posts))
            } catch (ex: Exception) {
                ex.printStackTrace()
                mutableState.postValue(Error(ex))
            }
        }
    }

    fun updatePosts(postsModel: PostsModel) {
        mutableState.value = Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val postsResponse =
                    postsRemoteRepository.updatePostsById(postsModel.id, postsModel.toRequest())
                val posts = postsResponse.toModel()
                mutableState.postValue(SuccessfullyUpdatePosts(posts))
            } catch (ex: Exception) {
                ex.printStackTrace()
                mutableState.postValue(Error(ex))
            }
        }
    }

    fun deletePosts(postsModel: PostsModel) {
        mutableState.value = Loading()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mutableState.postValue(SuccessfullyDeletePosts(postsModel.id))
            } catch (ex: Exception) {
                ex.printStackTrace()
                mutableState.postValue(Error(ex))
            }
        }
    }
}