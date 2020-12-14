package hi.iwansyy.usecase

import hi.iwansyy.domain.PostsDomain

interface PostsUseCaseInterface {
    suspend fun getAllPosts(): List<PostsDomain>
    suspend fun getPostsById(id: Int): PostsDomain
    suspend fun insertPosts(body: PostsDomain): PostsDomain
    suspend fun updatePosts(id: Int, body: PostsDomain): PostsDomain
    suspend fun deletePosts(id: Int): PostsDomain
}

/*

suspend fun getAllTodo(): List<TodoDomain>
suspend fun getTodoById(id: Int): TodoDomain
suspend fun insertTodo(body: TodoDomain): TodoDomain
suspend fun updateTodo(id: Int, body: TodoDomain): TodoDomain
suspend fun deleteTodoById(id: Int): TodoDomain*/
