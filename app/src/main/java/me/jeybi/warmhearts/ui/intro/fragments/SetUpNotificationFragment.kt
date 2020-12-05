package me.jeybi.warmhearts.ui.intro.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.setup_notification.*
import me.jeybi.warmhearts.R
import me.jeybi.warmhearts.ui.BaseFragment

class SetUpNotificationFragment : BaseFragment(){

    override fun setLayoutId(): Int {
        return R.layout.setup_notification
    }

    override fun onViewDidCreate(view: View, savedInstanceState: Bundle?) {

        rvGiveAccess.setOnClickListener {
            startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
        }

    }

}