package com.ep.musiccollection.ui.musicList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ep.musiccollection.R
import com.ep.musiccollection.databinding.ItemMusicListBinding
import com.ep.musiccollection.model.Music

class MusicListAdapter : RecyclerView.Adapter<MusicListAdapter.ViewHolder>() {
    private lateinit var musicList: List<Music>
    private var onClickCallBack: ((Music) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMusicListBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_music_list,
                parent, false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = if (::musicList.isInitialized) musicList.size else 0

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) = holder.bind(musicList[pos])

    fun updateMusicList(musicList: List<Music>) {
        this.musicList = musicList
        notifyDataSetChanged()
    }

    fun setOnItemClick(onClickCall: ((Music) -> Unit)) {
        onClickCallBack = onClickCall
    }

    inner class ViewHolder(private val binding: ItemMusicListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(music: Music) {
            binding.music = music
            binding.root.setOnClickListener {
                onClickCallBack!!.invoke(music)
            }
            binding.executePendingBindings()
        }
    }
}