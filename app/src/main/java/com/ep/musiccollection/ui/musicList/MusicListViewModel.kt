package com.ep.musiccollection.ui.musicList

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel;
import com.ep.musiccollection.data.Resource
import com.ep.musiccollection.data.repository.MusicRepository
import com.ep.musiccollection.model.MusicListResult

class MusicListViewModel : ViewModel() {
    private val repository = MusicRepository()
    private var musicListData = MediatorLiveData<Resource<MusicListResult>>()

    fun getMusicListData(): MediatorLiveData<Resource<MusicListResult>> {
        return musicListData
    }


    fun getMusicList(searchTerm: String) {
        musicListData
            .addSource(repository.getMusicList(searchTerm)) { musicListData.value = it }
    }
}
