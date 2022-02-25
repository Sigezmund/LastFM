package teach.meskills.lastfm.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import teach.meskills.lastfm.data.ContentRepositoryRx
import teach.meskills.lastfm.data.User

class UserViewModel(private val contentRepository: ContentRepositoryRx) : ViewModel() {

    val userLiveData = MutableLiveData<User>()
    val isSuccessfullyEnter = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<Boolean>()
    private var disposable: Disposable? = null
    init {
        RxJavaPlugins.setErrorHandler {

        }
    }

    fun onSignInClick(login: String, password: String) {
        disposable?.dispose()

        disposable = contentRepository.signIn(login, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ isSuccess ->
                isSuccessfullyEnter.value = isSuccess
                errorMessage.value = !isSuccess
                if (isSuccess) {
                    userLiveData.value = User(login, password)
                }
            }, { error ->
                isSuccessfullyEnter.value = false
                errorMessage.value = true
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}
