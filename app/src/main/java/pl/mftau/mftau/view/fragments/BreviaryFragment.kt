package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_breviary.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.utils.tryToRunFunctionOnInternet
import pl.mftau.mftau.viewmodel.MainViewModel

class BreviaryFragment : Fragment() {

    private lateinit var mViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_breviary, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.breviaryToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        val position = BreviaryFragmentArgs.fromBundle(requireArguments()).position
        view.breviaryToolbarTitle.text = resources.getStringArray(R.array.breviary_list)[position]

        activity?.let {
            mViewModel = ViewModelProvider(it).get(MainViewModel::class.java)
            when {
                savedInstanceState != null -> view.breviaryText.restoreState(savedInstanceState)
                mViewModel.wasBreviaryLoaded(position) -> loadBreviary()
                else -> activity?.tryToRunFunctionOnInternet { loadBreviary() }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        view?.breviaryText?.saveState(outState)
    }

    private fun loadBreviary() {
        val loadingDialog = AlertDialog.Builder(requireActivity())
            .setView(R.layout.dialog_loading)
            .setOnCancelListener { findNavController().navigateUp() }
            .create()
        loadingDialog.show()
        mViewModel.loadBreviaryHtml(
            BreviaryFragmentArgs.fromBundle(requireArguments()).position,
            loadingDialog,
            requireView().breviaryText,
            requireActivity()
        )
    }
}
