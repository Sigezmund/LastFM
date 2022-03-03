package teach.meskills.lastfm.data

data class AudioResponseEntity(
    val requestState: RequestState,
    val trackList: List<AudioEntity>
)