package teach.meskills.lastfm.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import teach.meskills.lastfm.data.ContentRepositoryOkhttp
import teach.meskills.lastfm.data.User
import java.lang.Exception

class UserViewModel(private val contentRepository: ContentRepositoryOkhttp) : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)
    val userLiveData = MutableLiveData<User>()
    val isSuccessfullyEnter = MutableLiveData<Boolean?>()

    fun onSignInClick(login: String, password: String) {
        scope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    contentRepository.signIn(login, password)
                }
                isSuccessfullyEnter.value = response
                Log.d("res", response.toString())
                if (response) {
                    userLiveData.value = User(login, password)
                }
                else {
                    isSuccessfullyEnter.value = false
                }

            } catch (e: Exception) {
                e.stackTraceToString()
                isSuccessfullyEnter.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}
//2.0/?method=chart.gettoptracks&api_key=YOUR_API_KEY&format=json