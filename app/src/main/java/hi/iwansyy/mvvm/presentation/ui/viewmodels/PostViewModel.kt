package hi.iwansyy.mvvm.presentation.ui.viewmodels

import androidx.lifecycle.*
import hi.iwansyy.mvvm.domain.PostsDomain
import hi.iwansyy.mvvm.presentation.ui.states.StatePosts
import hi.iwansyy.mvvm.presentation.ui.views.activities.PostsUseCaseInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
class PostViewModel(private val useCase: PostsUseCaseInterface) : BaseViewModel() {
    private val mutableState by lazy { MutableLiveData<StatePosts>() }
    val state : LiveData<StatePosts> get() = mutableState

    companion object{
        fun getFactory(useCase: PostsUseCaseInterface): ViewModelProvider.Factory{
            return object : ViewModelProvider.Factory{
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return PostViewModel(useCase) as T
                }
            }
        }
    }

    fun getAllPosts(){
        callCoroutines {
            val posts = useCase.getAllPosts()
            mutableState.postValue((StatePosts.getAllPostsSuccessfully(posts)))
        }
    }

    fun getPosts(id: Int){
        callCoroutines {
            val posts = useCase.getPostsById(id)
            mutableState.postValue(StatePosts.getPostsDetailSuccessfully(posts))
        }
    }

    fun insertPosts(domain: PostsDomain){
        callCoroutines {
            val posts = useCase.insertPosts(domain)
            mutableState.postValue(StatePosts.SuccessfullyInsertPosts(posts))
        }
    }

    fun updatePosts(domain: PostsDomain){
        callCoroutines {
            val posts = useCase.updatePosts(domain.id, domain)
            mutableState.postValue(StatePosts.SuccessfullyUpdatePosts(posts))
        }
    }

    fun deletePosts(id: Int){
        callCoroutines {
            val posts = useCase.deletePosts(id)
            mutableState.postValue(StatePosts.SuccessfullyDeletePosts(posts))
        }
    }

    private fun callCoroutines(onSuccess: suspend () -> Unit) {
        mutableState.value = StatePosts.Loading()

        val job by lazy {
            viewModelScope.launch(Dispatchers.IO){
                try {
                    onSuccess()
                } catch (ex: Exception){
                    onError(ex)
                }
            }
        }
        jobs.add(job)
    }

    private fun onError(ex: Exception) {
        ex.printStackTrace()
        mutableState.postValue(StatePosts.Error(ex))
    }


    /*private val mutableState by lazy { MutableLiveData<StatePosts>() }
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
    }*/
}