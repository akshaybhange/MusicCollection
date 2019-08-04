package com.ep.musiccollection.data.repository

import androidx.lifecycle.MutableLiveData
import com.ep.musiccollection.data.Resource
import com.ep.musiccollection.data.api.MusicWebservice
import com.ep.musiccollection.model.MusicListResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://itunes.apple.com/"

class MusicRepository {
    private var musicWebservice: MusicWebservice = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MusicWebservice::class.java)

    fun getMusicList(searchTerm: String): MutableLiveData<Resource<MusicListResult>> {
        val musicListData = MutableLiveData<Resource<MusicListResult>>()
        musicListData.value = Resource.Loading()

        musicWebservice
            .getMusicList(searchTerm)
            .enqueue(
                object : Callback<MusicListResult> {
                    override fun onFailure(call: Call<MusicListResult>, t: Throwable) {
                        musicListData.value = Resource.Error("Error : ${t.message}")
                    }

                    override fun onResponse(call: Call<MusicListResult>, response: Response<MusicListResult>) {
                        if (response.isSuccessful && response.body() != null) {
                            musicListData.value = response.body()?.let { Resource.Success(it) }
                        } else {
                            musicListData.value = Resource.Error("Error in loading data")
                        }
                    }

                })

        return musicListData
    }

}