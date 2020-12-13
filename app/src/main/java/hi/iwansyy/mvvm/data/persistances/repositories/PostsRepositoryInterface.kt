package hi.iwansyy.mvvm.data.persistances.repositories
import hi.iwansyy.mvvm.domain.PostsDomain

interface PostsRepositoryInterface {
    suspend fun getAllPosts(): List<PostsDomain>
    suspend fun getPostsById(id: Int): PostsDomain
    suspend fun insertPosts(body: PostsDomain): PostsDomain
    suspend fun updatePosts(id: Int, body: PostsDomain): PostsDomain
    suspend fun deletePosts(id: Int): PostsDomain
}