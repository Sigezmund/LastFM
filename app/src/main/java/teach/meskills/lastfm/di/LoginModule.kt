package teach.meskills.lastfm.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import teach.meskills.lastfm.login.UserViewModel

val loginModule = module {
    viewModel {
        UserViewModel(contentRepository = get(named("okhttp")))
    }
}