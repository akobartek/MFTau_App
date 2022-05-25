package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import pl.mftau.mftau.databinding.FragmentSongBookPlaylistBinding

class SongBookPlaylistFragment : BindingFragment<FragmentSongBookPlaylistBinding>() {

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSongBookPlaylistBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        // TODO
    }
}