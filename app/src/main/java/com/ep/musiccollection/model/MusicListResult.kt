package com.ep.musiccollection.model

import com.google.gson.annotations.SerializedName

data class MusicListResult(
    val resultCount: Int,
    @SerializedName("results")
    val result: List<Music>
)