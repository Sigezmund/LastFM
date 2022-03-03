package teach.meskills.lastfm.chartTracks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import teach.meskills.lastfm.data.AudioEntity
import teach.meskills.lastfm.data.ContentRepositoryRx
import teach.meskills.lastfm.data.RequestState

class ChartViewModel(
    private val contentRepository: ContentRepositoryRx
) : ViewModel() {

    val trackLiveData = MutableLiveData<List<AudioEntity>>()
    val isRefreshing = MutableLiveData<Boolean>()
    private val errorMessage = MutableLiveData<Boolean>()
    private var disposable: Disposable? = null

    init {
        refreshData()
    }

    fun refreshData() {
        disposable?.dispose()
        disposable = contentRepository.getMedia()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                trackLiveData.value = it.trackList
                isRefreshing.value = it.requestState == RequestState.Loading
                errorMessage.value = it.requestState is RequestState.Error
            }, {

            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}
