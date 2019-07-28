package pl.mftau.mftau.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_breviary.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.viewmodel.MainViewModel
import pl.mftau.mftau.utils.tryToRunFunctionOnInternet

class BreviaryFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_breviary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let {
            mViewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)

            when {
                savedInstanceState != null -> view.breviaryText.restoreState(savedInstanceState)
                mViewModel.wasBreviaryLoaded(BreviaryFragmentArgs.fromBundle(arguments!!).position) -> loadBreviary()
                else -> activity?.tryToRunFunctionOnInternet { loadBreviary() }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        view?.breviaryText?.saveState(outState)
    }

    private fun loadBreviary() {
        val loadingDialog = AlertDialog.Builder(activity!!)
            .setView(R.layout.dialog_loading)
            .setOnCancelListener { findNavController().navigateUp() }
            .create()
        loadingDialog.show()
        mViewModel.loadBreviaryHtml(
            BreviaryFragmentArgs.fromBundle(arguments!!).position,
            loadingDialog,
            view!!.breviaryText,
            activity!!
        )
    }
}
