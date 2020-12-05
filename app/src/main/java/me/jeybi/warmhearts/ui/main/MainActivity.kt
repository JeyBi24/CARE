package me.jeybi.warmhearts.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import me.jeybi.warmhearts.R
import me.jeybi.warmhearts.ui.BaseActivity
import me.jeybi.warmhearts.ui.intro.IntroActivity
import me.jeybi.warmhearts.utils.Constants
import me.jeybi.warmhearts.utils.services.ForegroundService


class MainActivity : BaseActivity(){
    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onViewDidCreate(savedInstanceState: Bundle?) {

        if (!checkIfSetUp()){
            startActivity(Intent(this,IntroActivity::class.java))
            finish()
        }


        ContextCompat.startForegroundService(this, Intent(this, ForegroundService::class.java)
            .setAction(Constants.ACTION_START)
        )



    }

    private fun checkIfSetUp() : Boolean{
      return  sharedPreferences.getBoolean(Constants.PREF_SETUP,false)
    }



}

