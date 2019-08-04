package com.ep.musiccollection.data.api

import com.ep.musiccollection.model.MusicListResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val PARAM_SEARCH_TERM = "term"

interface MusicWebservice {
    @GET("search")
    fun getMusicList(@Query(PARAM_SEARCH_TERM) search: String): Call<MusicListResult>
}