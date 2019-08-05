package com.ep.musiccollection.ui.musicDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.ep.musiccollection.R
import com.ep.musiccollection.databinding.MusicDetailFragmentBinding
import com.google.android.material.snackbar.Snackbar

class MusicDetailFragment : Fragment() {
    private lateinit var viewModel: MusicDetailViewModel
    private lateinit var binding: MusicDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.music_detail_fragment, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MusicDetailViewModel::class.java)
        binding.viewModel = viewModel
        binding.music = arguments?.let { MusicDetailFragmentArgs.fromBundle(it).StringArgumentMusic }

        observeChanges()
    }

    private fun observeChanges() {
        viewModel.webPageClickData.observe(viewLifecycleOwner, Observer {
            it?.let {
                val url = it.getContentIfNotHandled()
                if (!url.isNullOrEmpty()) {
                    startActivity(
                        Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    )
                }
            }
        })
        viewModel.buyButtonClickData.observe(viewLifecycleOwner, Observer {
            it?.let {
                val music = it.getContentIfNotHandled()
                music?.let {
                    Snackbar.make(
                        binding.root,
                        getString(R.string.msgBoughtTrack, music.trackPrice, music.currency),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

}
