package me.jeybi.warmhearts.network

import me.jeybi.warmhearts.network.models.ChildrenDeathResponse
import me.jeybi.warmhearts.network.models.NoMoneyRate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EndPoints {

    @GET("dataset/{data_id}/version/29514")
    fun getChildrenDeathRate(@Path("data_id") data_id : Int, @Query("access_key") access_key : String ) : Call<ArrayList<ChildrenDeathResponse>>

    @GET("dataset/{data_id}/version/29514")
    fun getMoneyRate(@Path("data_id") data_id : Int, @Query("access_key") access_key : String ) : Call<ArrayList<NoMoneyRate>>

    @GET("dataset/{data_id}/version/29514")
    fun getIllnessRate(@Path("data_id") data_id : Int, @Query("access_key") access_key : String ) : Call<ArrayList<ChildrenDeathResponse>>

    @GET("dataset/{data_id}/version/29514")
    fun getInfatnDeath(@Path("data_id") data_id : Int, @Query("access_key") access_key : String ) : Call<ArrayList<ChildrenDeathResponse>>

    @GET("dataset/{data_id}/version/29514")
    fun getRetirement(@Path("data_id") data_id : Int, @Query("access_key") access_key : String ) : Call<ArrayList<NoMoneyRate>>


}
