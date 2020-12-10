package hi.iwansyy.mvvm.model

import android.os.Parcelable
import hi.iwansyy.mvvm.repository.PostsRequest
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostsModel(
	var id: Int = 0,
	var title: String,
	var body: String,
	var userId: Int = 0
) : Parcelable

fun PostsModel.toRequest() = PostsRequest(title, body)

