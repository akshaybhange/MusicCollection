package com.ep.musiccollection.ui.musicList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ep.musiccollection.R

class MusicListFragment : Fragment() {

    companion object {
        fun newInstance() = MusicListFragment()
    }

    private lateinit var viewModel: MusicListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.music_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MusicListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
