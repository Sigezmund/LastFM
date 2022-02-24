package teach.meskills.lastfm.chartTracks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.koin.core.component.KoinComponent
import teach.meskills.lastfm.R
import teach.meskills.lastfm.data.AudioEntity

class RecyclerAdapter(
    private val clickListener: (AudioEntity) -> Unit
) : RecyclerView.Adapter<AudioViewHolder>(), KoinComponent {

    var audio: List<AudioEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        return AudioViewHolder(
            clickListener,
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