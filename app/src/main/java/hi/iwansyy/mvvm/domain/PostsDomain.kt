package hi.iwansyy.mvvm.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostsDomain(
    var id: Int,
    var title: String?,
    var body: String?,
    var userId: Int
): Parcelable