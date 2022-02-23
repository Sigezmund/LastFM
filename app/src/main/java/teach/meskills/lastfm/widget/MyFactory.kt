package teach.meskills.lastfm.widget

import android.R
import android.appwidget.AppWidgetManager
import android.content.Intent
import android.widget.RemoteViews
import android.widget.RemoteViewsService.RemoteViewsFactory


//class MyFactory internal constructor(ctx: Context, intent: Intent) : RemoteViewsFactory {
//    var data: ArrayList<String>? = null
//    var context: Context
//    var sdf: SimpleDateFormat
//    var widgetID: Int
//    override fun onCreate() {
//        data = ArrayList()
//    }
//
//    override fun getCount(): Int {
//        return data!!.size()
//    }
//
//    override fun getItemId(position: Int): Long {
//        return position.toLong()
//    }
//
//    override fun getLoadingView(): RemoteViews {
//        return null
//    }
//
//    override fun getViewAt(position: Int): RemoteViews {
//        val rView = RemoteViews(
//            context.getPackageName(),
//            R.layout.item
//        )
//        rView.setTextViewText(R.id.tvItemText, data!![position])
//        return rView
//    }
//
//    override fun getViewTypeCount(): Int {
//        return 1
//    }
//
//    override fun hasStableIds(): Boolean {
//        return true
//    }
//
//    override fun onDataSetChanged() {
//        data!!.clear()
//        data!!.add(sdf.format(Date(System.currentTimeMillis())))
//        data!!.add(hashCode().toString())
//        data!!.add(widgetID.toString())
//        for (i in 3..14) {
//            data!!.add("Item $i")
//        }
//    }
//
//    override fun onDestroy() {}
//
//    init {
//        context = ctx
//        sdf = SimpleDateFormat("HH:mm:ss")
//        widgetID = intent.getIntExtra(
//            AppWidgetManager.EXTRA_APPWIDGET_ID,
//            AppWidgetManager.INVALID_APPWIDGET_ID
//        )
//    }
//}