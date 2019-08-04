package com.ep.musiccollection.util

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ep.musiccollection.data.Resource
import com.ep.musiccollection.model.Music

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

    @JvmStatic
    @BindingAdapter("setProgressVisibility")
    fun setProgressVisibility(view: ProgressBar, resource: Resource<Any>) {
        view.visibility = when (resource) {
            is Resource.Loading -> View.VISIBLE
            else -> View.GONE
        }
    }

}