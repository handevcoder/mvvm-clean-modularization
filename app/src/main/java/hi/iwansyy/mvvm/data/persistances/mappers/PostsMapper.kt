package hi.iwansyy.mvvm.data.persistances.mappers

import hi.iwansyy.mvvm.data.payload.api.posts.PostsRequest
import hi.iwansyy.mvvm.data.payload.api.posts.PostsResponse
import hi.iwansyy.mvvm.domain.PostsDomain

class PostsMapper: PostsMapperInterface {
    override fun toDomainList(responses: List<PostsResponse>): List<PostsDomain>{
        return responses.asSequence().map { toDomain(it) }.toList()
    }

    override fun toDomain(response: PostsResponse): PostsDomain {
        return PostsDomain(response.id, response.title, response.body,response.userId)
    }

    override fun toRequest(domain: PostsDomain): PostsRequest {
        return PostsRequest(domain.title, domain.body)
    }
}