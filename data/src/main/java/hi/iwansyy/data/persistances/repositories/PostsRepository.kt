package hi.iwansyy.data.persistances.repositories

import hi.iwansyy.data.persistances.contracts.PostsPersistanceContract
import hi.iwansyy.data.persistances.mappers.PostsMapperInterface
import hi.iwansyy.mvvm.domain.PostsDomain

class PostsRepository(
        private val persistance: PostsPersistanceContract,
        private val mapper: PostsMapperInterface
): PostsRepositoryInterface {

    override suspend fun getAllPosts(): List<PostsDomain> {
        return mapper.toDomainList(persistance.getAllPosts())
    }

    override suspend fun getPostsById(id: Int): PostsDomain {
        return mapper.toDomain(persistance.getPostsById(id))
    }

    override suspend fun insertPosts(body: PostsDomain): PostsDomain {
        return mapper.toDomain(persistance.insertPosts(mapper.toRequest(body)))

    }

    override suspend fun updatePosts(id: Int, body: PostsDomain): PostsDomain {
        return mapper.toDomain(persistance.updatePosts(id, mapper.toRequest(body)))
    }

    override suspend fun deletePosts(id: Int): PostsDomain {
        return mapper.toDomain(persistance.deletePosts(id))
    }
}