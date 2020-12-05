package me.jeybi.warmhearts.ui.lockscreen

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.view.GestureDetector.SimpleOnGestureListener
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.romainpiel.shimmer.Shimmer
import com.romainpiel.shimmer.ShimmerTextView

import me.jeybi.warmhearts.R
import me.jeybi.warmhearts.utils.Constants
import java.text.SimpleDateFormat
import java.util.*


class LockScreenActivity : Activity() {

    lateinit var shimmer : Shimmer

     var screenNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)

        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        decorView.systemUiVisibility = uiOptions

        setUpLockScreen()

    }

    private fun setUpLockScreen() {

        val sharedPref = getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        screenNumber = sharedPref.getInt(Constants.PREF_LOCK_NUMBER, 0)

        when(screenNumber){
            0 -> {
                showScreen0()
            }
            1 -> {
                showScreen1()
            }
            2 -> {
                showScreen2()
            }
            3 -> {
                showScreen3()
            }
            4 -> {
                showScreen4()
            }
        }
        sharedPref.edit().putInt(Constants.PREF_LOCK_NUMBER,if (screenNumber!=4) ++screenNumber else 0).apply()
    }




    fun showScreen0(){
        setContentView(R.layout.lock_screen0)
        setUpGesture()
        setUpTime()

    }

    private fun showScreen1() {
        setContentView(R.layout.lock_screen1)
        setUpGesture()
        setUpTime()
    }


    private fun showScreen2() {
        setContentView(R.layout.lock_screen2)
        setUpGesture()
        setUpTime()
    }
    private fun showScreen3() {
        setContentView(R.layout.lock_screen3)
        setUpGesture()
        setUpTime()
    }
    private fun showScreen4() {
        setContentView(R.layout.lock_screen4)
        setUpGesture()
        setUpTime()
    }


        private fun setUpTime() {
        val currentTime: String = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        val textViewTime = findViewById<TextView>(R.id.textViewTime)

        textViewTime.text = currentTime
    }

    private fun setUpGesture(){
        shimmer = Shimmer()

        shimmer.start(findViewById<ShimmerTextView>(R.id.textSwipe))

        val containerLockScreen =  findViewById<ConstraintLayout>(R.id.containerLockScreen)
        val rvApply = findViewById<RelativeLayout>(R.id.rvApply)

        val customGestureListener = GestureDetector(object : CustomGestureListener(
            containerLockScreen
        ) {

            override fun onSwipeRight(): Boolean {
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                return false
            }

            override fun onSwipeLeft(): Boolean {
                finish()
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                return false
            }


        })

        containerLockScreen.setOnTouchListener { v, event ->
            customGestureListener.onTouchEvent(event)
            true
        }

        rvApply?.setOnClickListener {
            val url = "https://pdp.uz/all-course"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
            finish()
        }
    }


    override fun onBackPressed() {

    }

    abstract class CustomGestureListener(private val mView: View) :
        SimpleOnGestureListener() {
        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            mView.onTouchEvent(e)
            return super.onSingleTapConfirmed(e)
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return false
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {


            if (e1.x < e2.x) {
                return onSwipeRight()
            }
            return if (e1.x > e2.x) {
                onSwipeLeft()
            } else false
        }

        abstract fun onSwipeRight(): Boolean
        abstract fun onSwipeLeft(): Boolean
    }

    override fun onDestroy() {
        super.onDestroy()
        shimmer.cancel()
    }



}