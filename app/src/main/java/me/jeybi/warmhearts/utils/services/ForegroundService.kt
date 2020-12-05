package me.jeybi.warmhearts.utils.services

import android.app.KeyguardManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import me.jeybi.warmhearts.R
import me.jeybi.warmhearts.ui.lockscreen.LockScreenActivity
import me.jeybi.warmhearts.utils.Constants


class ForegroundService : Service() {


    override fun onCreate() {
        super.onCreate()

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterOverlayReceiver()
    }



    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        if (intent.action==Constants.ACTION_START){
            createNotificationChannel()
            registerOverlayReceiver()
            startForeground(
                1,
                NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("You are earning money to donate.")
                    .setContentText("Every time you unlock the phone, you help 1 needy person!")
                    .setSubText("Little Effort, Big Support")
                    .setSmallIcon(R.drawable.logo)
                    .setColor(ContextCompat.getColor(this, R.color.darkMain))
                    .setShowWhen(false)
                    .build()
            )
        }else{
            stopForeground(true)
            stopSelfResult(startId)
        }

        return START_REDELIVER_INTENT
    }

    private fun registerOverlayReceiver() {
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        filter.addAction(Intent.ACTION_USER_PRESENT)
        filter.addAction(Intent.ACTION_USER_UNLOCKED)
        registerReceiver(overlayReceiver, filter)
    }

    private val TAG = "ForeGroundService"
    var mWindowManager: WindowManager? = null
    var mView: View? = null
    var mAnimation: Animation? = null


    private val overlayReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            Log.d(TAG, "[onReceive]$action")

            val km = getSystemService(KEYGUARD_SERVICE) as KeyguardManager
           val kl = km.newKeyguardLock("name")


            if (action==Intent.ACTION_SCREEN_OFF){
                kl.disableKeyguard()
            }

            if (action == Intent.ACTION_SCREEN_OFF) {
                startActivity(
                    Intent(
                        context,
                        LockScreenActivity::class.java
                    ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
        }
    }


    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                resources.getString(R.string.service_channel),
                NotificationManager.IMPORTANCE_LOW
            )
            channel.setShowBadge(false)
            getSystemService(NotificationManager::class.java)?.createNotificationChannel(channel)
        }
    }

    companion object {
        const val CHANNEL_ID: String = "service_channel"
    }

    private fun unregisterOverlayReceiver() {
        unregisterReceiver(overlayReceiver)
    }



}