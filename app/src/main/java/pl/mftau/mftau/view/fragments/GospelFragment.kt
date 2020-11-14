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
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_gospel.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.PreferencesManager
import pl.mftau.mftau.utils.tryToRunFunctionOnInternet
import pl.mftau.mftau.viewmodel.MainViewModel
import java.util.*

class GospelFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mTextToSpeech: TextToSpeech

    private var mGospel: String? = null
    private var mIsSpeaking: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_gospel, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inflateToolbarMenu(view.gospelToolbar)

        activity?.let {
            mViewModel = ViewModelProvider(it).get(MainViewModel::class.java)

            when {
                savedInstanceState != null -> view.gospelText.restoreState(savedInstanceState)
                mViewModel.wasGospelLoaded() -> loadGospel()
                else -> activity?.tryToRunFunctionOnInternet { loadGospel() }
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
                    view.handler.postDelayed({ readGospel() }, 1500)
                else
                    view.handler.post { updateToolbarIcon(view.gospelToolbar.menu) }
            }

            @Suppress("OverridingDeprecatedMember")
            override fun onError(utteranceId: String?) {
            }

            override fun onStart(utteranceId: String?) {}
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        view?.gospelText?.saveState(outState)
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
                            it.icon =
                                ContextCompat.getDrawable(
                                    requireContext(),
                                    R.drawable.anim_pause_to_sound
                                )
                            (it.icon as AnimatedVectorDrawable).start()
                            mTextToSpeech.stop()
                        } else {
                            mIsSpeaking = true
                            it.icon =
                                ContextCompat.getDrawable(
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
        val loadingDialog = AlertDialog.Builder(requireContext())
            .setView(R.layout.dialog_loading)
            .setOnCancelListener { findNavController().navigateUp() }
            .create()
        loadingDialog.show()
        mViewModel.loadGospelHtml(loadingDialog, requireView().gospelText, requireActivity())
    }

    private fun readGospel() {
        if (!mViewModel.wasGospelLoaded())
            return

        if (mGospel == null) {
            mGospel = mViewModel.getGospelHtml()
            mGospel = mGospel!!.substring(mGospel!!.indexOf("<p>"), mGospel!!.length)
                .replace("<p>", "")
                .replace("</p>", "\n")
                .replace("<br>", "")
                .replace("<strong>", "")
                .replace("</strong>", "")
                .replace(":", ".")

            val list = mGospel!!.split("; ").toMutableList()
            val stringBuilder = StringBuilder()
            for (sentence in list) {
                stringBuilder.append(sentence.capitalize(Locale.getDefault())).append(". ")
            }
            mGospel = stringBuilder.toString().trim()
            mGospel = mGospel!!.substring(0, mGospel!!.length - 1)
        }

        mTextToSpeech.speak(mGospel, TextToSpeech.QUEUE_FLUSH, Bundle().apply {
            putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "UtteranceID")
        }, "UtteranceID")
    }
}
