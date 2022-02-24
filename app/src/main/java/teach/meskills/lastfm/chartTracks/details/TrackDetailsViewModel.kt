package teach.meskills.lastfm.chartTracks.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import teach.meskills.lastfm.data.AudioEntity

class TrackDetailsViewModel(audio: AudioEntity) : ViewModel() {

    val audio: LiveData<AudioEntity> = MutableLiveData(audio)
}