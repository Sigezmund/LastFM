package teach.meskills.lastfm.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import teach.meskills.lastfm.data.AppDatabase
import teach.meskills.lastfm.data.ContentRepository
import teach.meskills.lastfm.data.ContentRepositoryOkhttp
import teach.meskills.lastfm.data.ContentRepositoryRetrofit

val appBaseModule = module {
    factory<AppDatabase> { AppDatabase.build(context = get()) }
    factory<ContentRepository>(okhttpQualifier) { ContentRepositoryOkhttp(appDatabase = get()) }
    factory<ContentRepository>(retrofitQualifier) { ContentRepositoryRetrofit() }

}

val okhttpQualifier = named("okhttp")
val retrofitQualifier = named("retrofit")