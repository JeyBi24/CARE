package me.jeybi.warmhearts.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import me.jeybi.warmhearts.R
import me.jeybi.warmhearts.network.EndPoints
import me.jeybi.warmhearts.network.ServiceBuilder
import me.jeybi.warmhearts.network.models.ChildrenDeathResponse
import me.jeybi.warmhearts.network.models.NoMoneyRate
import me.jeybi.warmhearts.ui.BaseActivity
import me.jeybi.warmhearts.ui.intro.IntroActivity
import me.jeybi.warmhearts.utils.Constants
import me.jeybi.warmhearts.utils.services.ForegroundService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : BaseActivity() {
    override fun setLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun onViewDidCreate(savedInstanceState: Bundle?) {

        if (!checkIfSetUp()) {
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }

        val serviceStarted = sharedPreferences.getBoolean(Constants.SERVICE_STARTED, false)
        switchTurnOn.isChecked = serviceStarted

        switchTurnOn.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
               sharedPreferences.edit().putBoolean(Constants.SERVICE_STARTED,true).apply()
                ContextCompat.startForegroundService(
                    this, Intent(this, ForegroundService::class.java)
                        .setAction(Constants.ACTION_START)
                )

            } else {
                sharedPreferences.edit().putBoolean(Constants.SERVICE_STARTED,false).apply()

                ContextCompat.startForegroundService(
                    this, Intent(this, ForegroundService::class.java)
                        .setAction(Constants.ACTION_STOP)
                )

            }
        })

        getChildrenDeathRate()
        getNoMoneyRate()
        getIllnessRate()
        getInfantDeathRate()
        getRetirementRate()
    }

    private fun checkIfSetUp(): Boolean {
        return sharedPreferences.getBoolean(Constants.PREF_SETUP, false)
    }

    fun getChildrenDeathRate(){
        val request = ServiceBuilder.buildService(this,EndPoints::class.java,false)
        val call = request.getChildrenDeathRate(Constants.OPEN_DATA_DEATH_RATE_ID,Constants.OPEN_DATA_ACCESS_KEY)

        call.enqueue(object : Callback<ArrayList<ChildrenDeathResponse>> {
            override fun onResponse(
                call: Call<ArrayList<ChildrenDeathResponse>>,
                response: Response<ArrayList<ChildrenDeathResponse>>
            ) {

                if (response.isSuccessful&&response.body()!=null) {
                    val data = response.body()!![0]
                    textDeathCases.text = data.g2
                }
            }

            override fun onFailure(call: Call<ArrayList<ChildrenDeathResponse>>, t: Throwable) {


            }
        })

    }

    fun getNoMoneyRate(){
        val request = ServiceBuilder.buildService(this,EndPoints::class.java,false)
        val call = request.getMoneyRate(Constants.OPEN_DATA_MONEY_RATE_ID,Constants.OPEN_DATA_ACCESS_KEY)

        call.enqueue(object : Callback<ArrayList<NoMoneyRate>> {
            override fun onResponse(
                call: Call<ArrayList<NoMoneyRate>>,
                response: Response<ArrayList<NoMoneyRate>>
            ) {

                if (response.isSuccessful&&response.body()!=null) {
                    val data = response.body()!![0]
                    textNoMoney.text = data.G2
                }
            }

            override fun onFailure(call: Call<ArrayList<NoMoneyRate>>, t: Throwable) {


            }
        })
    }

    fun getIllnessRate(){
        val request = ServiceBuilder.buildService(this,EndPoints::class.java,false)
        val call = request.getIllnessRate(Constants.OPEN_DATE_ILNESS,Constants.OPEN_DATA_ACCESS_KEY)

        call.enqueue(object : Callback<ArrayList<ChildrenDeathResponse>> {
            override fun onResponse(
                call: Call<ArrayList<ChildrenDeathResponse>>,
                response: Response<ArrayList<ChildrenDeathResponse>>
            ) {

                if (response.isSuccessful&&response.body()!=null) {
                    val data = response.body()!![0]
                    textIllness.text = data.g9
                }
            }

            override fun onFailure(call: Call<ArrayList<ChildrenDeathResponse>>, t: Throwable) {


            }
        })
    }

    fun getInfantDeathRate(){
        val request = ServiceBuilder.buildService(this,EndPoints::class.java,false)
        val call = request.getInfatnDeath(Constants.OPEN_DATA_INFANTDEATH,Constants.OPEN_DATA_ACCESS_KEY)

        call.enqueue(object : Callback<ArrayList<ChildrenDeathResponse>> {
            override fun onResponse(
                call: Call<ArrayList<ChildrenDeathResponse>>,
                response: Response<ArrayList<ChildrenDeathResponse>>
            ) {

                if (response.isSuccessful&&response.body()!=null) {
                    val data = response.body()!![0]
                    textInfantDeath.text = data.g2
                }
            }

            override fun onFailure(call: Call<ArrayList<ChildrenDeathResponse>>, t: Throwable) {


            }
        })
    }


    fun getRetirementRate(){
        val request = ServiceBuilder.buildService(this,EndPoints::class.java,false)
        val call = request.getRetirement(Constants.OPEN_DATA_RETIREMENT,Constants.OPEN_DATA_ACCESS_KEY)

        call.enqueue(object : Callback<ArrayList<NoMoneyRate>> {
            override fun onResponse(
                call: Call<ArrayList<NoMoneyRate>>,
                response: Response<ArrayList<NoMoneyRate>>
            ) {

                if (response.isSuccessful&&response.body()!=null) {
                    val data = response.body()!![0]
                    textRetirement.text = data.G7
                }
            }

            override fun onFailure(call: Call<ArrayList<NoMoneyRate>>, t: Throwable) {


            }
        })
    }


}

