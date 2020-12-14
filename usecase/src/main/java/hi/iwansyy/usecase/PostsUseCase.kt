package hi.iwansyy.usecase

import hi.iwansyy.data.persistances.repositories.PostsRepositoryInterface

class PostsUseCase(private val repository: PostsRepositoryInterface): PostsUseCaseInterface {
    override suspend fun getAllPosts(): List<hi.iwansyy.domain.PostsDomain> {
        return repository.getAllPosts()
    }

    override suspend fun getPostsById(id: Int): hi.iwansyy.domain.PostsDomain {
        return repository.getPostsById(id)
    }

    override suspend fun insertPosts(body: hi.iwansyy.domain.PostsDomain): hi.iwansyy.domain.PostsDomain {
        return repository.insertPosts(body)
    }

    override suspend fun updatePosts(id: Int, body: hi.iwansyy.domain.PostsDomain): hi.iwansyy.domain.PostsDomain {
        return repository.updatePosts(id, body)
    }

    override suspend fun deletePosts(id: Int): hi.iwansyy.domain.PostsDomain {
        return repository.deletePosts(id)
    }
}