package teach.meskills.lastfm.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import teach.meskills.lastfm.chartTracks.ChartViewModel

val chartModule = module {
    viewModel { ChartViewModel(get(named("okhttp"))) }
}