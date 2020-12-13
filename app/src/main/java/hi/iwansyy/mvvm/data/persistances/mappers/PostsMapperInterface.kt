package hi.iwansyy.mvvm.data.persistances.mappers

import hi.iwansyy.mvvm.data.payload.api.posts.PostsRequest
import hi.iwansyy.mvvm.data.payload.api.posts.PostsResponse
import hi.iwansyy.mvvm.domain.PostsDomain

interface PostsMapperInterface {
    fun toDomainList(responses: List<PostsResponse>): List<PostsDomain>
    fun toDomain(response: PostsResponse): PostsDomain
    fun toRequest(domain: PostsDomain): PostsRequest
}