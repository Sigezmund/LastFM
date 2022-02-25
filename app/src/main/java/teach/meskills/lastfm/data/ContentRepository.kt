package teach.meskills.lastfm.data

import io.reactivex.rxjava3.core.Single

interface ContentRepository {
    suspend fun getMedia(): List<AudioEntity>
    suspend fun signIn(login: String, password: String): Boolean
}

interface ContentRepositoryRx {
    fun getMedia(): Single<List<AudioEntity>>
    fun signIn(login: String, password: String): Single<Boolean>
}