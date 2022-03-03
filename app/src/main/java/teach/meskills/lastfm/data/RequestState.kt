package teach.meskills.lastfm.data

sealed class RequestState {
    object Loading : RequestState()
    object Data : RequestState()
    data class Error(val error: Throwable) : RequestState()
}