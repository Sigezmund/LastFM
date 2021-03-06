package teach.meskills.lastfm.chartTracks

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import teach.meskills.lastfm.data.AudioEntity
import teach.meskills.lastfm.R

class RecyclerAdapter : RecyclerView.Adapter<AudioViewHolder>(){

    var audio: List<AudioEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        return AudioViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.audio_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val item = audio[position]
        holder.input(item)
    }

    override fun getItemCount() = audio.size

}