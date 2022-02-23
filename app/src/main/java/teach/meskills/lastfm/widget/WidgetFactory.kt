package teach.meskills.lastfm.widget

import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService.RemoteViewsFactory
import teach.meskills.lastfm.R
import teach.meskills.lastfm.data.AppDatabase
import teach.meskills.lastfm.data.AudioEntity


class WidgetFactory(
    val context: Context, val intent: Intent
) : RemoteViewsFactory {

    private lateinit var appDatabase: AppDatabase
    private lateinit var audio: List<AudioEntity>

    override fun onCreate() {
        appDatabase = AppDatabase.build(context)
        audio = emptyList()
    }

    override fun getCount(): Int {
        return audio.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getViewAt(position: Int): RemoteViews {
        val item = audio[position]
        val remoteViews = RemoteViews(
            context.packageName,
            R.layout.item
        )
        remoteViews.setTextViewText(R.id.nameWidget, item.name)
        remoteViews.setTextViewText(R.id.artistWidget, item.artist)
        return remoteViews
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun onDataSetChanged() {
        audio = appDatabase.audioDao().getAudio().take(3)
    }

    override fun onDestroy() {}
}
