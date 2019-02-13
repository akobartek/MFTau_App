package pl.mftau.mftau.view.fragments

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_gospel.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.viewmodel.MainViewModel
import java.lang.StringBuilder
import java.util.*

class GospelFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel
    private lateinit var mTextToSpeech: TextToSpeech

    private var mGospel: String? = null
    private var mIsSpeaking: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_gospel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            mViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)

            when {
                savedInstanceState != null -> view.gospelText.restoreState(savedInstanceState)
                mViewModel.wasGospelLoaded() -> loadGospel()
                else -> checkNetworkConnection()
            }
        }

        mTextToSpeech = TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = mTextToSpeech.setLanguage(Locale("pl_PL"))
                mTextToSpeech.setSpeechRate(0.9f)

                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED)
                    Toast.makeText(context, "Language not supported", Toast.LENGTH_LONG).show()
            } else {
                Log.e("TextToSpeech", "Initialization failed!")
            }
        })
        mTextToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onDone(utteranceId: String?) {
                view.handler.post { activity?.invalidateOptionsMenu() }
//                view.handler.postDelayed({ readGospel() }, 2000)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) =
            inflater.inflate(R.menu.menu_gospel, menu)

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if (mIsSpeaking) {
            mIsSpeaking = false
            val playGospelMenuItem = menu.findItem(R.id.action_play_gospel)
            playGospelMenuItem.icon = context!!.getDrawable(R.drawable.anim_pause_to_sound)
            (playGospelMenuItem.icon as AnimatedVectorDrawable).start()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_play_gospel -> {
            if (mIsSpeaking) {
                mIsSpeaking = false
                item.icon = context!!.getDrawable(R.drawable.anim_pause_to_sound)
                (item.icon as AnimatedVectorDrawable).start()
                mTextToSpeech.stop()
            } else {
                mIsSpeaking = true
                item.icon = context!!.getDrawable(R.drawable.anim_sound_to_pause)
                (item.icon as AnimatedVectorDrawable).start()
                readGospel()
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun loadGospel() {
        val loadingDialog = AlertDialog.Builder(activity!!)
                .setView(R.layout.dialog_loading)
                .setOnCancelListener { findNavController().navigateUp() }
                .create()
        loadingDialog.show()
        mViewModel.loadGospelHtml(loadingDialog, view!!.gospelText, activity!!)
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
            for (sentence in list) { stringBuilder.append(sentence.capitalize()).append(". ") }
            mGospel = stringBuilder.toString().trim()
            mGospel = mGospel!!.substring(0, mGospel!!.length - 1)
        }

        mTextToSpeech.speak(mGospel, TextToSpeech.QUEUE_FLUSH, Bundle().apply {
            putString(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "UtteranceID")
        }, "UtteranceID")
    }

    private fun showNoInternetDialog() =
            AlertDialog.Builder(context!!)
                    .setTitle(R.string.no_internet_title)
                    .setMessage(R.string.no_internet_reconnect_message)
                    .setCancelable(false)
                    .setPositiveButton(R.string.try_again) { dialog, _ ->
                        dialog.dismiss()
                        checkNetworkConnection()
                    }
                    .setNegativeButton(R.string.cancel) { dialog, _ ->
                        dialog.dismiss()
                        findNavController().navigateUp()
                    }
                    .create()
                    .show()

    private fun checkNetworkConnection() {
        val connectivityManager = activity!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        val activeNetworkInfo = connectivityManager?.activeNetworkInfo

        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            loadGospel()
        } else {
            showNoInternetDialog()
        }
    }
}
