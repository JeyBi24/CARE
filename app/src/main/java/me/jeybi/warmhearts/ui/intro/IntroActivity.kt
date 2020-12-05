package me.jeybi.warmhearts.ui.intro

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.github.paolorotolo.appintro.AppIntro2
import com.github.paolorotolo.appintro.AppIntro2Fragment
import com.github.paolorotolo.appintro.model.SliderPagerBuilder
import me.jeybi.warmhearts.R
import me.jeybi.warmhearts.ui.main.MainActivity

class IntroActivity : AppIntro2() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,

            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        showIntroSlides()

    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        startActivity(Intent(this,SetUpActivity::class.java))
        finish()
    }

    private fun showIntroSlides() {

        val pageOne = SliderPagerBuilder()
            .title("WELCOME TO 'CARE'")
            .description("The Platform where where Warm hearts of Uzbekistan help other needy people")
            .imageDrawable(R.drawable.intro_1)
            .bgColor(Color.parseColor("#b088f9"))
            .titleTypefaceRes(R.font.g_black)
            .descTypefaceRes(R.font.g_semi_bold)
            .build()

        val pageTwo = SliderPagerBuilder()
            .title("Habit of 21st century")
            .description("According to statistics, each Phone User checks their phone at least 96 times a day. And they see the same LockScreen 96 times a day which is quite boring")
            .imageDrawable(R.drawable.intro_2)
            .bgColor(Color.parseColor("#fc8621"))
            .titleTypefaceRes(R.font.g_black)

            .descTypefaceRes(R.font.g_semi_bold)
            .build()

        val pageThree =SliderPagerBuilder()
            .title("Interactive LockScreen")
            .description("What if there is little Advertisement on Hyperactive and Flexible Animated LockScreen Wallpapers ?")
            .imageDrawable(R.drawable.intro_3)
            .bgColor(Color.parseColor("#98acf8"))
            .titleTypefaceRes(R.font.g_black)
            .descTypefaceRes(R.font.g_semi_bold)
            .build()

        val pageFour = SliderPagerBuilder()
            .title("Minimal Effort\nBig Support")
            .description("Every-time people UNLOCK their phone, they can help a child or 1 needy person in Uzbekistan, which means they can donate even without spending money from their pocket.")
            .imageDrawable(R.drawable.intro_4)
            .bgColor(Color.parseColor("#61b15a"))
            .titleTypefaceRes(R.font.g_black)
            .descTypefaceRes(R.font.g_semi_bold)
            .build()

        val pageFive = SliderPagerBuilder()
            .title("Together We can make lives better")
            .description("All the money coming from Advertisements on your LockScreen will directly go for Charity.\nDisabled People, Children and Society need your help and you can do it effortlessly.")
            .imageDrawable(R.drawable.intro_5)
            .bgColor(Color.parseColor("#ffa45b"))
            .titleTypefaceRes(R.font.g_black)
            .descTypefaceRes(R.font.g_semi_bold)
            .build()


        addSlide(AppIntro2Fragment.newInstance(pageOne))
        addSlide(AppIntro2Fragment.newInstance(pageTwo))
        addSlide(AppIntro2Fragment.newInstance(pageThree))
        addSlide(AppIntro2Fragment.newInstance(pageFour))
        addSlide(AppIntro2Fragment.newInstance(pageFive))

        setImmersiveMode(true)

        setProgressIndicator()
    }


}