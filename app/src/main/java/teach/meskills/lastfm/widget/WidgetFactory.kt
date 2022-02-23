package teach.meskills.lastfm.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService.RemoteViewsFactory
import teach.meskills.lastfm.R
import teach.meskills.lastfm.data.AudioEntity


class WidgetFactory(
    var context: Context,val audio:List<AudioEntity>) :
    RemoteViewsFactory {


    override fun onCreate() {
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


    }
//    override fun onDataSetChanged() {
//        data.clear()
//        data.add(sdf.format(Date(System.currentTimeMillis())))
//        data.add(hashCode().toString())
//        data.add(widgetID.toString())
//        for (i in 3..14) {
//            data.add("Item $i")
//        }
//    }

    override fun onDestroy() {}
}

//class MyWidgetProvider : AppWidgetProvider() {
//    override fun onUpdate(
//        context: Context, appWidgetManager: AppWidgetManager,
//        appWidgetIds: IntArray
//    ) {
//        Log.w(LOG, "onUpdate method called")
//        // Get all ids
//        val thisWidget = ComponentName(
//            context,
//            MyWidgetProvider::class.java
//        )
//        val allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget)
//
//        // Build the intent to call the service
//        val intent = Intent(
//            context.applicationContext,
//            UpdateWidgetService::class.java
//        )
//        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds)
//
//        // Update the widgets via the service
//        context.startService(intent)
//    }

//    companion object {
//        private const val LOG = "widget.com.com"
//    }
