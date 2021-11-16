package com.psdu.notify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.psdu.tools.NotificationsUtils

class MainActivity : AppCompatActivity() {

    lateinit var notifyStatus: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notifyStatus = findViewById(R.id.tvx_notify)
        checkNotifyStatus(notifyStatus)

        notifyStatus.setOnClickListener {
            NotificationsUtils.openPush(this)
        }
    }

    /**
     * oppo、vivo、realme等通知栏权限默认是关闭
     * 检查通知开关状态
     */
    private fun checkNotifyStatus(tvNotifyStatus: TextView?) {
        val notificationEnabled = NotificationsUtils.isNotificationEnabled(this)
        Log.e("推送权限TAG-", "$notificationEnabled")

        if (notificationEnabled) {
            tvNotifyStatus?.text = "通知权限开关：打开\n(点击打开系统推送设置页面)"
        } else {
            tvNotifyStatus?.text = "通知权限开关：关闭"
        }
    }

    override fun onResume() {
        super.onResume()
        checkNotifyStatus(notifyStatus)
    }
}