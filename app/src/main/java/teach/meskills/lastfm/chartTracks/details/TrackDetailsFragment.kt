package teach.meskills.lastfm.chartTracks.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import teach.meskills.lastfm.data.AudioEntity
import teach.meskills.lastfm.databinding.TrackDetailsFragmentBinding

class TrackDetailsFragment : Fragment() {

    private lateinit var binding: TrackDetailsFragmentBinding
    private val audio: AudioEntity get() = requireArguments().getParcelable(EXTRA_AUDIO)!!
    private val viewModel: TrackDetailsViewModel by inject(parameters = { parametersOf(audio) })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TrackDetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.audio.observe(viewLifecycleOwner) {
            binding.audioTrack.text = it.name + " " + it.artist
        }
    }

    companion object {
        private const val EXTRA_AUDIO = "audio"
        fun newInstance(audio: AudioEntity): TrackDetailsFragment {
            return TrackDetailsFragment().apply {
                arguments = bundleOf(EXTRA_AUDIO to audio)
            }
        }
    }
}