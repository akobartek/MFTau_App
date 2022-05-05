package pl.mftau.mftau.model

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.parcelize.Parcelize
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.GlideApp
import kotlin.random.Random

@Parcelize
data class Member(
    var id: String = "",
    var name: String = "",
    var city: String = "",
    var isResponsible: Boolean = false
) : Parcelable {

    companion object {
        @BindingAdapter("memberPhoto")
        @JvmStatic
        fun loadImage(view: ImageView, member: Member?) {
            if ("example@mftau.pl" != FirebaseAuth.getInstance().currentUser!!.email!!) {
                val storageReference = FirebaseStorage.getInstance()
                    .reference.child("members/${member?.id}.jpg")

                GlideApp.with(view.context)
                    .load(storageReference)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user)
                    .into(view)
            } else {
                val randomNumber = Random.nextInt(100)
                val address = if (member != null) {
                    if (member.name.last() == 'a')
                        "https://randomuser.me/api/portraits/women/$randomNumber.jpg"
                    else
                        "https://randomuser.me/api/portraits/men/$randomNumber.jpg"
                } else ""

                GlideApp.with(view.context)
                    .load(address)
                    .circleCrop()
                    .placeholder(R.drawable.ic_user)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(view)
            }
        }
    }
}