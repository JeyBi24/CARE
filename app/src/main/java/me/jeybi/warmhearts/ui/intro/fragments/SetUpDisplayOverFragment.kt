package me.jeybi.warmhearts.ui.intro.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import kotlinx.android.synthetic.main.setup_display.*
import me.jeybi.warmhearts.R
import me.jeybi.warmhearts.ui.BaseFragment
import me.jeybi.warmhearts.ui.intro.SetUpActivity

class SetUpDisplayOverFragment : BaseFragment(){
    override fun setLayoutId(): Int {
        return R.layout.setup_display
    }

    override fun onViewDidCreate(view: View, savedInstanceState: Bundle?) {

        rvGiveAccess.setOnClickListener {
            (activity as SetUpActivity).startActivityForResult(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION), 1)
        }

    }
}