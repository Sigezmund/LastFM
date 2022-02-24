package teach.meskills.lastfm.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import teach.meskills.lastfm.chartTracks.details.TrackDetailsViewModel
import teach.meskills.lastfm.data.AudioEntity

val trackDetailsModule = module {
    viewModel { (audio: AudioEntity) ->
        TrackDetailsViewModel(audio)
    }
}