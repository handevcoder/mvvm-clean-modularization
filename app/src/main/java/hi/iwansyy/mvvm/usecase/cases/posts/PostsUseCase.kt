package hi.iwansyy.mvvm.usecase.cases.posts

import hi.iwansyy.mvvm.data.persistances.repositories.PostsRepositoryInterface
import hi.iwansyy.mvvm.domain.PostsDomain
import hi.iwansyy.mvvm.presentation.ui.views.activities.PostsUseCaseInterface

class PostsUseCase(private val repository: PostsRepositoryInterface): PostsUseCaseInterface {
    override suspend fun getAllPosts(): List<PostsDomain> {
        return repository.getAllPosts()
    }

    override suspend fun getPostsById(id: Int): PostsDomain {
        return repository.getPostsById(id)
    }

    override suspend fun insertPosts(body: PostsDomain): PostsDomain {
        return repository.insertPosts(body)
    }

    override suspend fun updatePosts(id: Int, body: PostsDomain): PostsDomain {
        return repository.updatePosts(id, body)
    }

    override suspend fun deletePosts(id: Int): PostsDomain {
        return repository.deletePosts(id)
    }
}