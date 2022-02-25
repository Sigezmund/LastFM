package teach.meskills.lastfm.data

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonElement
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.security.MessageDigest

class ContentRepositoryRxImpl : ContentRepositoryRx {
    private val gson = Gson()
    private val okHttpClient = OkHttpClient.Builder().build()
    override fun getMedia(): Single<List<AudioEntity>> {
        TODO("Not yet implemented")
    }

    override fun signIn(login: String, password: String): Single<Boolean> {
        return Single.create { source ->
            try {
                val apiSignature =
                    "api_key" + ContentRepositoryOkhttp.APIKEY + "methodauth.getMobileSessionpassword" + password +
                            "username" + login + ContentRepositoryOkhttp.APISIG
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
                    "method=auth.getMobileSession&api_key=" + ContentRepositoryOkhttp.APIKEY + "&password=" + password +
                            "&username=" + login + "&api_sig=" + hexString + "&format=json"
                val urlAdress = "https://ws.audioscrobbler.com/2.0/?$urlParameter"
                Log.d("re", urlAdress)
                val response =
                    okHttpClient
                        .newCall(
                            Request.Builder()
                                .url(urlAdress)
                                .post(RequestBody.create(null, ByteArray(0)))
                                .build()
                        ).execute()
                val jsonString = response.body?.string()
                Log.d("respon", jsonString.toString())
                gson.fromJson(jsonString, JsonElement::class.java)
                Log.d("respon", response.code.toString())
                source.onSuccess(response.isSuccessful)
            } catch (e: Exception) {
                source.onError(e)
            }
        }
    }
}