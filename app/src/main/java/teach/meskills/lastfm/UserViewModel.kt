package teach.meskills.lastfm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonElement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.lang.Exception
import java.security.MessageDigest

class UserViewModel : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)
    private val okHttpClient = OkHttpClient.Builder().build()
    val userLiveData = MutableLiveData<User>()
    val isSuccessfullyEnter = MutableLiveData<Boolean>()
    private val gson = Gson()

    fun onSignInClick(login: String, password: String) {
        scope.launch {
            try {
                val apiSignature =
                    "api_key" + APIKEY + "methodauth.getMobileSessionpassword" + password +
                            "username" + login + APISIG
                val hexString = StringBuilder()
                val digest: MessageDigest = MessageDigest.getInstance("MD5")
                digest.update(apiSignature.toByteArray(charset("UTF-8")))
                val messageDigest: ByteArray = digest.digest()
                for (aMessageDigest in messageDigest) {
                    var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                    while (h.length < 2) h = "0$h"
                    hexString.append(h)
                }
                val urlParameter =
                    "method=auth.getMobileSession&api_key=" + APIKEY + "&password=" + password +
                            "&username=" + login + "&api_sig=" + hexString + "&format=json"
                val urlAdress = "https://ws.audioscrobbler.com/2.0/?$urlParameter"

                val response = withContext(Dispatchers.IO) {
                    okHttpClient
                        .newCall(
                            Request.Builder()
                                .url(urlAdress)
                                .post(RequestBody.create(null, ByteArray(0)))
                                .build()
                        ).execute()
                }
                userLiveData.value = User(login, password)
                val jsonString = response.body?.string()
                Log.d("respon", jsonString.toString())
                val json = gson.fromJson(jsonString, JsonElement::class.java)
                Log.d("respon", response.code.toString())
                isSuccessfullyEnter.value = response.code == 200
            } catch (e: Exception) {
                isSuccessfullyEnter.value = false
            }
        }
    }

    companion object {
        val APIKEY: String = "6323fb2dc68af795505f60f05c4323c1"
        val APISIG: String = "3d788657600fa1a51146673fc38c3207"
    }
}