package com.ep.musiccollection.util

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ep.musiccollection.R
import com.ep.musiccollection.model.Music
import com.google.android.material.button.MaterialButton

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("setMusicImage")
    fun setMusicImage(imageView: ImageView, music: Music) {
        Glide.with(imageView)
            .load(music.artworkUrl60)
            .thumbnail(
                Glide.with(imageView)
                    .load(music.artworkUrl30)
                    .centerInside()
            )
            .centerInside()
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("setCircularMusicImage")
    fun setCircularMusicImage(imageView: ImageView, music: Music) {
        Glide.with(imageView)
            .load(music.artworkUrl100)
            .thumbnail(
                Glide.with(imageView)
                    .load(music.artworkUrl30)
                    .centerInside()
                    .circleCrop()
            )
            .centerInside()
            .circleCrop()
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("setReleaseDate")
    fun setReleaseDate(view: TextView, date: String) {
        if (date.isEmpty() || date.length < 10) {
            view.text = " - "
        } else {
            view.text = date.substring(0, 10)
        }
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("setTrackLength")
    fun setTrackLength(view: TextView, millis: Int) {
        val minutes = millis / 1000 / 60
        val seconds = millis / 1000 % 60
        view.text = "$minutes:$seconds"
    }


    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("setTrackPrice")
    fun setTrackPrice(view: MaterialButton, music: Music) {
        view.text = view.context.getString(R.string.setTrackPrice, music.trackPrice, music.currency)
    }

}