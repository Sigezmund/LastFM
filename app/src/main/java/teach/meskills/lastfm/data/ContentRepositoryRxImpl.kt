package teach.meskills.lastfm.data

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonElement
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import teach.meskills.lastfm.chartTracks.AudioResponseDTO
import java.security.MessageDigest
import java.util.concurrent.TimeUnit

class ContentRepositoryRxImpl(
    private val appDatabase: AppDatabase
) : ContentRepositoryRx {
    private val gson = Gson()
    private val okHttpClient = OkHttpClient.Builder().build()
    override fun getMedia(): Observable<AudioResponseEntity> {
        return Observable.combineLatest(
            appDatabase.audioDao().getAudioObservable(),
            getMediaFromNetwork()
        ) { list, requestState ->
            AudioResponseEntity(requestState, list)
        }
    }


    private fun getMediaFromNetwork(): Observable<RequestState> {
        return Observable.create<RequestState?> { emitter ->
            try {
                val urlParameter =
                    "method=chart.gettoptracks&api_key=${ContentRepositoryOkhttp.APIKEY}&format=json"
                val urlAdress = "https://ws.audioscrobbler.com/2.0/?$urlParameter"
                val response =
                    okHttpClient
                        .newCall(
                            Request.Builder()
                                .url(urlAdress)
                                .post(RequestBody.create(null, ByteArray(0)))
                                .build()
                        ).execute()

                val jsonString = response.body?.string().orEmpty()
                Log.d("respon", jsonString)
                val json = gson.fromJson(jsonString, AudioResponseDTO::class.java)
                Log.d("respon", response.code.toString())
                val trackMap = json.tracks.track?.map {
                    AudioEntity(
                        artist = it.artist.name,
                        name = it.name,
                        image = it.image[1].text
                    )
                }.orEmpty()
                appDatabase.audioDao().saveAudio(trackMap)
                emitter.onNext(RequestState.Data)
            } catch (e: java.lang.Exception) {
                emitter.onNext(RequestState.Error(e))
            }
            emitter.onComplete()
        }
            .delay(2000L, TimeUnit.MILLISECONDS)
            .startWithItem(RequestState.Loading)
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