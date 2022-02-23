package teach.meskills.lastfm.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import teach.meskills.lastfm.R


class WidgetProvider : AppWidgetProvider() {

    override fun onUpdate(
        context: Context, appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        for (i in appWidgetIds) {
            updateWidget(context, appWidgetManager, i)
        }
    }

    private fun updateWidget(
        context: Context, appWidgetManager: AppWidgetManager,
        appWidgetId: Int
    ) {
        val remoteViews = RemoteViews(context.packageName, R.layout.widget)
        updateRemoteAdapter(remoteViews, context, appWidgetId)
//        setList(remoteViews, context, appWidgetId)
        appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.widgetList)
    }

    private fun updateRemoteAdapter(remoteViews: RemoteViews, context: Context, appWidgetId: Int) {
        remoteViews.setImageViewResource(R.id.refresh, R.drawable.ic_refresh)
        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }
        val adapterIntent = WidgetService.getIntent(context)
        remoteViews.setRemoteAdapter(R.id.widgetList, adapterIntent)
        ////Формируем actionIntent для pendingIntent  чтобы обрабатывать клики элементы виджета
        val actionIntent = Intent(context, WidgetProvider::class.java).apply {
            action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
        }
        val pendingIntent = PendingIntent.getBroadcast(context, appWidgetId, actionIntent, flags)
        remoteViews.setPendingIntentTemplate(R.id.widgetList, pendingIntent)

        val intentUpdate = Intent(context, WidgetProvider::class.java)
        intentUpdate.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        //Назначим обновление всем инстансам виджета
        val ids = AppWidgetManager.getInstance(context)
            .getAppWidgetIds(ComponentName(context!!, WidgetProvider::class.java))
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
        //Назначим этот intent как PendingIntent, используя PendingIntent.getBroadcast()
        val pendingUpdate = PendingIntent.getBroadcast(
            context,
            appWidgetId,
            intentUpdate,
            flags
        )
        //Назначим этот pendingIntent откликом на нажатие пользователем кнопки ‘Обновить’
        remoteViews.setOnClickPendingIntent(R.id.refresh, pendingUpdate)
    }

}
