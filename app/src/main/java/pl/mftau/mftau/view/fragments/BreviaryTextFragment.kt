package pl.mftau.mftau.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.DialogBreviarySelectOfficeBinding
import pl.mftau.mftau.databinding.FragmentBreviaryTextBinding
import pl.mftau.mftau.utils.showNoInternetDialogWithTryAgain
import pl.mftau.mftau.utils.tryToRunFunctionOnInternet
import pl.mftau.mftau.viewmodel.BreviaryViewModel

class BreviaryTextFragment : BindingFragment<FragmentBreviaryTextBinding>() {

    private lateinit var mViewModel: BreviaryViewModel
    private val mLoadingDialog: AlertDialog by lazy {
        MaterialAlertDialogBuilder(requireContext())
            .setView(R.layout.dialog_loading)
            .setOnCancelListener { findNavController().navigateUp() }
            .create()
    }

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentBreviaryTextBinding.inflate(inflater, container, false)

    override fun setup(savedInstanceState: Bundle?) {
        binding.breviaryToolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        val position = BreviaryTextFragmentArgs.fromBundle(requireArguments()).position
        binding.breviaryToolbarTitle.text =
            resources.getStringArray(R.array.breviary_list)[position]

        activity?.let {
            mViewModel = ViewModelProvider(it)[BreviaryViewModel::class.java]
            when {
                savedInstanceState != null -> binding.breviaryText.restoreState(savedInstanceState)
                else -> activity?.tryToRunFunctionOnInternet { checkIfThereAreMultipleOffices() }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.breviaryText.saveState(outState)
    }

    private fun checkIfThereAreMultipleOffices() {
        mLoadingDialog.show()
        mViewModel.checkIfThereAreMultipleOffices(
            BreviaryTextFragmentArgs.fromBundle(requireArguments()).daysFromToday,
            { offices /* offices are pairs of <code, name> */ ->
                if (offices != null) showSelectOfficeDialog(offices)
                else loadBreviary()
            }, {
                if (mLoadingDialog.isShowing) mLoadingDialog.hide()
                activity?.showNoInternetDialogWithTryAgain { checkIfThereAreMultipleOffices() }
            })
    }

    private fun showSelectOfficeDialog(offices: List<Pair<String, String>>) {
        if (mLoadingDialog.isShowing) mLoadingDialog.hide()

        val dialogBinding = DialogBreviarySelectOfficeBinding.inflate(layoutInflater)
        dialogBinding.selectOfficeList.apply {
            adapter = ArrayAdapter(
                requireContext(),
                R.layout.dialog_item_listview_select,
                offices.map { it.second })
            setItemChecked(0, true)
            onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
                setItemChecked(position, true)
            }
        }

        MaterialAlertDialogBuilder(requireContext())
            .setView(dialogBinding.root)
            .setCancelable(false)
            .setPositiveButton(R.string.load) { dialog, _ ->
                dialog.dismiss()
                mLoadingDialog.show()
                loadBreviary(offices[dialogBinding.selectOfficeList.checkedItemPosition].first)
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                dialog.dismiss()
                findNavController().navigateUp()
            }
            .create()
            .show()
    }

    private fun loadBreviary(office: String = "") {
        mViewModel.loadBreviaryHtml(
            office,
            BreviaryTextFragmentArgs.fromBundle(requireArguments()).daysFromToday,
            BreviaryTextFragmentArgs.fromBundle(requireArguments()).position,
            { textToShow ->
                if (mLoadingDialog.isShowing) mLoadingDialog.hide()
                binding.breviaryText.apply {
                    loadDataWithBaseURL(
                        null, textToShow, "text/html", "UTF-8", null
                    )
                    visibility = View.VISIBLE
                    scrollTo(0, 0)
                    animate().alpha(1f).duration = 444L
                }
            }, {
                if (mLoadingDialog.isShowing) mLoadingDialog.hide()
                activity?.showNoInternetDialogWithTryAgain { loadBreviary(office) }
            }
        )
    }
}
