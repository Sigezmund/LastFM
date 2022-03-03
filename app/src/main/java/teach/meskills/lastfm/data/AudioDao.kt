package teach.meskills.lastfm.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable

@Dao
interface AudioDao {
    @Insert
    fun saveAudio(audio: List<AudioEntity>)

    @Query("SELECT * FROM AudioEntity")
    fun getAudio(): List<AudioEntity>

    @Query("SELECT * FROM AudioEntity")
    fun getAudioObservable(): Observable<List<AudioEntity>>
}