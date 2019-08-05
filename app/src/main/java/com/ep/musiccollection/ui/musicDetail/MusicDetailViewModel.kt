package com.ep.musiccollection.ui.musicDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.ep.musiccollection.model.Music
import com.ep.musiccollection.util.Event

class MusicDetailViewModel : ViewModel() {
    var webPageClickData = MutableLiveData<Event<String>>()
    var buyButtonClickData = MutableLiveData<Event<Music>>()

    fun onClickOpenWebPage(url: String) {
        webPageClickData.value = Event(url)
    }

    fun onClickBuyButton(music: Music) {
        buyButtonClickData.value = Event(music)
    }
}
