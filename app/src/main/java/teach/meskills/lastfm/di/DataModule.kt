package teach.meskills.lastfm.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import teach.meskills.lastfm.data.AppDatabase
import teach.meskills.lastfm.data.ContentRepository
import teach.meskills.lastfm.data.ContentRepositoryOkhttp
import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttp


@Module
@InstallIn(FragmentComponent::class)
object DataModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.build(context)
    }

}

@Module
@InstallIn(FragmentComponent::class)
abstract class RepoModule {

    @OkHttp
    @Binds
    abstract fun providerContentRepository(repo: ContentRepositoryOkhttp): ContentRepository

}