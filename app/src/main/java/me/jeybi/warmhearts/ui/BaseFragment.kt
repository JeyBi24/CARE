package me.jeybi.warmhearts.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import me.jeybi.warmhearts.utils.Constants

abstract class BaseFragment : Fragment(){

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedPreferences = context!!.getSharedPreferences(Constants.APP_PREFERENCES,Context.MODE_PRIVATE)
        return inflater.inflate(setLayoutId(),container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewDidCreate(view,savedInstanceState)
    }

    @LayoutRes
    abstract fun setLayoutId() : Int

    abstract fun onViewDidCreate(view: View, savedInstanceState: Bundle?)

}