package com.psdu.tools


import android.content.Intent

import android.os.Build

import android.app.Activity
import android.content.Context
import android.provider.Settings

import androidx.core.app.NotificationManagerCompat


object NotificationsUtils {
    fun isNotificationEnabled(context: Context): Boolean {
        return NotificationManagerCompat.from(context.getApplicationContext())
            .areNotificationsEnabled()
    }

    fun openPush(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //这种方案适用于 API 26, 即8.0（含8.0）以上可以用
            val intent = Intent()
            intent.action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, activity.packageName)
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, activity.applicationInfo.uid)
            activity.startActivity(intent)
        } else {
            PermissionUtil.toPermissionSetting(activity)
        }
    }
}