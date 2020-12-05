package me.jeybi.warmhearts

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import me.jeybi.warmhearts.utils.Constants

class CareApp : Application() {

    lateinit var sharedPreferences : SharedPreferences

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
    }
}