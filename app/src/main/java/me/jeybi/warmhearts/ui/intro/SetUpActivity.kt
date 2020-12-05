package me.jeybi.warmhearts.ui.intro

import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.text.TextUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import me.jeybi.warmhearts.R
import me.jeybi.warmhearts.ui.BaseActivity
import me.jeybi.warmhearts.ui.intro.fragments.SetUpAdminFragment
import me.jeybi.warmhearts.ui.intro.fragments.SetUpDisplayOverFragment
import me.jeybi.warmhearts.ui.intro.fragments.SetUpNotificationFragment
import me.jeybi.warmhearts.ui.main.MainActivity
import me.jeybi.warmhearts.utils.Constants
import me.jeybi.warmhearts.utils.receivers.AdminReceiver

class SetUpActivity : BaseActivity() {

    private val isDeviceAdmin: Boolean
        get() {
            return (getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager)
                .isAdminActive(ComponentName(this, AdminReceiver::class.java))
        }





    override fun setLayoutId(): Int {
        return R.layout.activity_set_up
    }

    override fun onViewDidCreate(savedInstanceState: Bundle?) {
        if (!isDeviceAdmin)
            swapContentFragment(SetUpAdminFragment())
        else if (if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                !Settings.canDrawOverlays(this)
            } else {
                true
            }
        )
            swapContentFragment(SetUpDisplayOverFragment())



    }

    private fun swapContentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            .replace(R.id.contentSetUP, fragment, null)
            .commitAllowingStateLoss()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            swapContentFragment(SetUpDisplayOverFragment())
        }else if (requestCode==1){
            sharedPreferences.edit().putBoolean(Constants.PREF_SETUP,true).apply()
            startActivity(Intent(this@SetUpActivity,MainActivity::class.java))
            finish()
        }

    }


}