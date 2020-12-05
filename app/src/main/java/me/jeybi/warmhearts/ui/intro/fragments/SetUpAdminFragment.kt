package me.jeybi.warmhearts.ui.intro.fragments

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.setup_admin.*
import me.jeybi.warmhearts.R
import me.jeybi.warmhearts.ui.BaseFragment
import me.jeybi.warmhearts.ui.intro.SetUpActivity
import me.jeybi.warmhearts.utils.receivers.AdminReceiver

class SetUpAdminFragment : BaseFragment(){

    override fun setLayoutId(): Int {
        return R.layout.setup_admin
    }

    override fun onViewDidCreate(view: View, savedInstanceState: Bundle?) {

        rvGiveAccess.setOnClickListener {
            setAdminApp()
        }

    }



    fun setAdminApp(){
        (activity as SetUpActivity).startActivityForResult(Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN).apply {
            putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, ComponentName(activity!!, AdminReceiver::class.java))
        }, 0)


    }




}