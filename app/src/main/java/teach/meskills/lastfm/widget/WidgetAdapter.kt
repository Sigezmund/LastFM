package teach.meskills.lastfm.widget

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import teach.meskills.lastfm.R
import teach.meskills.lastfm.data.AudioEntity
import java.lang.Exception

class WidgetAdapter(val context: Context) : BaseAdapter() {
    var tracks: List<AudioEntity> = emptyList()

    override fun getCount(): Int = 3

    override fun getItem(position: Int): AudioEntity = tracks[position]

    override fun getItemId(position: Int): Long = position.toLong()

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup): View {
        val audio = tracks[position]

        var view = LayoutInflater.from(context).inflate(R.layout.audio_item, viewGroup, false)
        if (convertView != null) {
            view = convertView
        }
        view.findViewById<TextView>(R.id.name).text = audio.name
        view.findViewById<TextView>(R.id.artist).text = audio.artist
        val image = view.findViewById<ImageView>(R.id.image)
        try {
            Glide.with(context).clear(image)
            Glide.with(context)
                .load(audio.image)
                .into(image)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return view
    }
}