package teach.meskills.lastfm.data

class ContentRepositoryRetrofit : ContentRepository {
    override suspend fun getMedia(): List<AudioEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(login: String, password: String): Boolean {
        TODO("Not yet implemented")
    }
}