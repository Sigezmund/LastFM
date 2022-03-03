package teach.meskills.lastfm.data

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ContentRepository {
    suspend fun getMedia(): List<AudioEntity>
    suspend fun signIn(login: String, password: String): Boolean
}

interface ContentRepositoryRx {
    fun getMedia(): Observable<AudioResponseEntity>
    fun signIn(login: String, password: String): Single<Boolean>
}