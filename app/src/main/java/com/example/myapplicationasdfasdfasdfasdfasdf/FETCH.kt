package com.example.myapplicationasdfasdfasdfasdfasdf

import com.example.myapplicationasdfasdfasdfasdfasdf.DATA.User
import com.example.myapplicationasdfasdfasdfasdfasdf.Power.Power
import com.example.myapplicationasdfasdfasdfasdfasdf.SIGNALS.Matavimas
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET



// services
interface MatApiService {
    @GET("matavimai")
    fun getMatavimai(): Call<List<Matavimas>>
}
interface UserApiService {
    @GET("vartotojai")
    fun getVartotojai(): Call<List<User>>
}
interface PowerApiService {
    @GET("stiprumai")
    fun getPower(): Call<List<Power>>
}

//client
object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:5279/"

    val mat: MatApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MatApiService::class.java)
    }
    val usr: UserApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApiService::class.java)
    }
    val pwr: PowerApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PowerApiService::class.java)
    }
}



//   Read
suspend fun ReadMatavimaiFromApi(): List<Matavimas>? {
    return withContext(Dispatchers.IO) {
        try {
            val response = ApiClient.mat.getMatavimai().awaitResponse()
            if (response.isSuccessful) {
                response.body()
            } else {
                null // Handle the API error response
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null // Handle network or other exceptions
        }
    }
}
suspend fun ReadUsersFromApi(): List<User>? {
    return withContext(Dispatchers.IO) {
        try {
            val response = ApiClient.usr.getVartotojai().awaitResponse()
            if (response.isSuccessful) {
                response.body()
            } else {
                null // Handle the API error response
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null // Handle network or other exceptions
        }
    }
}
suspend fun ReadPowersFromApi(): List<Power>? {
    return withContext(Dispatchers.IO) {
        try {
            val response = ApiClient.pwr.getPower().awaitResponse()
            if (response.isSuccessful) {
                response.body()
            } else {
                null // Handle the API error response
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null // Handle network or other exceptions
        }
    }
}