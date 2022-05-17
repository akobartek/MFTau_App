package pl.mftau.mftau.view.fragments

import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentGospelBinding
import pl.mftau.mftau.utils.PreferencesManager
import pl.mftau.mftau.utils.showNoInternetDialogWithTryAgain
import pl.mftau.mftau.utils.tryToRunFunctionOnInternet
import pl.mftau.mftau.viewmodel.GospelViewModel
import java.util.*

class GospelFragment : BindingFragment<FragmentGospelBinding>() {

    private lateinit var mViewModel: GospelViewModel
    private lateinit var mTextToSpeech: TextToSpeech

    private var mIsSpeaking: Boolean = false

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentGospelBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        inflateToolbarMenu(binding.gospelToolbar)

        activity?.let {
            mViewModel = ViewModelProvider(it)[GospelViewModel::class.java]

            when {
                savedInstanceState != null -> binding.gospelText.restoreState(savedInstanceState)
                else -> it.tryToRunFunctionOnInternet { loadGospel() }
            }
        }

        mTextToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = mTextToSpeech.setLanguage(Locale("pl_PL"))
                mTextToSpeech.setSpeechRate(0.9f)

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    Toast.makeText(context, "Language not supported", Toast.LENGTH_LONG).show()
            } else {
                Log.e("TextToSpeech", "Initialization failed!")
            }
        }
        mTextToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onDone(utteranceId: String?) {
                if (PreferencesManager.getRepeatGospel())
                    requireView().handler.postDelayed({ readGospel() }, 1500)
                else
                    requireView().handler.post { updateToolbarIcon(binding.gospelToolbar.menu) }
            }

            @Deprecated("This method is deprecated, but is required to implement")
            override fun onError(utteranceId: String?) {
            }

            override fun onStart(utteranceId: String?) {}
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.gospelText.saveState(outState)
    }

    override fun onDestroy() {
        if (mTextToSpeech.isSpeaking) {
            mTextToSpeech.stop()
            mTextToSpeech.shutdown()
        }
        super.onDestroy()
    }

    private fun inflateToolbarMenu(toolbar: Toolbar) {
        toolbar.apply {
            setNavigationOnClickListener { findNavController().navigateUp() }
            inflateMenu(R.menu.menu_gospel)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_play_gospel -> {
                        if (mIsSpeaking) {
                            mIsSpeaking = false
                            it.icon = ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.anim_pause_to_sound
                            )
                            (it.icon as AnimatedVectorDrawable).start()
                            mTextToSpeech.stop()
                        } else {
                            mIsSpeaking = true
                            it.icon = ContextCompat.getDrawable(
                                requireContext(),
                                R.drawable.anim_sound_to_pause
                            )
                            (it.icon as AnimatedVectorDrawable).start()
                            readGospel()
                        }
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun updateToolbarIcon(menu: Menu) {
        if (mIsSpeaking) {
            mIsSpeaking = false
            val playGospelMenuItem = menu.findItem(R.id.action_play_gospel)
            playGospelMenuItem.icon =
                ContextCompat.getDrawable(requireContext(), R.drawable.anim_pause_to_sound)
            (playGospelMenuItem.icon as AnimatedVectorDrawable).start()
        }
    }

    private fun loadGospel() {
        val loadingDialog = MaterialAlertDialogBuilder(requireContext())
            .setView(R.layout.dialog_loading)
            .setOnCancelListener { findNavController().navigateUp() }
            .create()
        loadingDialog.show()
        mViewModel.loadGospelHtml(
            { textToShow ->
                if (loadingDialog.isShowing) loadingDialog.hide()
                binding.gospelText.apply {
                    loadDataWithBaseURL(
                        null, textToShow,
                        "text/html", "UTF-8", null
                    )
                    visibility = View.VISIBLE
                    scrollTo(0, 0)
                    animate().alpha(1f).duration = 444L
                }
            }, {
                if (loadingDialog.isShowing) loadingDialog.hide()
                activity?.showNoInternetDialogWithTryAgain { loadGospel() }
            })
    }

    private fun readGospel() {
        mViewModel.getGospelToRead()?.let {
            mTextToSpeech.speak(it, TextToSpeech.QUEUE_FLUSH, Bundle().apply {
                putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "UtteranceID")
            }, "UtteranceID")
        }
    }
}
