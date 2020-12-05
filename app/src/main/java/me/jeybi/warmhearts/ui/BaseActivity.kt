package me.jeybi.warmhearts.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import me.jeybi.warmhearts.CareApp

abstract class BaseActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = (application as CareApp).sharedPreferences
        setContentView(setLayoutId())
        onViewDidCreate(savedInstanceState)

    }


    @LayoutRes
    abstract fun setLayoutId() : Int

    abstract fun onViewDidCreate(savedInstanceState: Bundle?)


}