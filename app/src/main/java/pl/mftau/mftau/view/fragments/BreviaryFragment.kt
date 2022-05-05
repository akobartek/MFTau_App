package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.FragmentBreviaryBinding
import pl.mftau.mftau.utils.tryToRunFunctionOnInternet
import pl.mftau.mftau.viewmodel.MainViewModel

class BreviaryFragment : BindingFragment<FragmentBreviaryBinding>() {

    private lateinit var mViewModel: MainViewModel

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentBreviaryBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        binding.breviaryToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        val position = BreviaryFragmentArgs.fromBundle(requireArguments()).position
        binding.breviaryToolbarTitle.text = resources.getStringArray(R.array.breviary_list)[position]

        activity?.let {
            mViewModel = ViewModelProvider(it)[MainViewModel::class.java]
            when {
                savedInstanceState != null -> binding.breviaryText.restoreState(savedInstanceState)
                mViewModel.wasBreviaryLoaded(position) -> loadBreviary()
                else -> activity?.tryToRunFunctionOnInternet { loadBreviary() }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.breviaryText.saveState(outState)
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
            binding.breviaryText,
            requireActivity()
        )
    }
}
