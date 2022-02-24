package teach.meskills.lastfm.chartTracks

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import teach.meskills.lastfm.R
import teach.meskills.lastfm.data.AudioEntity

@GlideModule
class AudioViewHolder(
    private val clickListener: (AudioEntity) -> Unit,
    view: View
) : RecyclerView.ViewHolder(view) {
    var audio: AudioEntity? = null
    val nameView = view.findViewById<TextView>(R.id.name)
    val artist = view.findViewById<TextView>(R.id.artist)
    val imageView = view.findViewById<ImageView>(R.id.image)

    init {
        view.setOnClickListener {
            audio?.let {
                clickListener.invoke(it)
            }
        }
    }

    fun input(audio: AudioEntity) {
        this.audio = audio
        nameView.text = audio.name
        artist.text = audio.artist
        try {
            Glide.with(imageView.context).clear(imageView)
            Glide.with(imageView.context)
                .load(audio.image)
                .into(imageView)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}