package teach.meskills.lastfm.widget

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.widget.RemoteViewsService
import teach.meskills.lastfm.data.AudioEntity


class WidgetService : RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory {
        return WidgetFactory(
            applicationContext,
            intent.getParcelableArrayListExtra<AudioEntity>(WIDGET_KEY).orEmpty()
        )
    }

    companion object {
        const val WIDGET_KEY = "widget key"

        fun getIntent(context: Context, list: List<AudioEntity>): Intent {
            val arrayList = ArrayList<AudioEntity>()
            arrayList.addAll(list.take(3))
            return Intent(context, WidgetService::class.java).apply {
                putParcelableArrayListExtra(WIDGET_KEY, arrayList)
            }
        }
    }
}