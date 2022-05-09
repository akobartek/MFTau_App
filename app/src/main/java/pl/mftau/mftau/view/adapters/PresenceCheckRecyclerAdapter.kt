package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import pl.mftau.mftau.R
import pl.mftau.mftau.databinding.DialogAbsenceBinding
import pl.mftau.mftau.databinding.ItemPresenceCheckBinding
import pl.mftau.mftau.model.local_db.Member
import pl.mftau.mftau.view.fragments.PresenceCheckFragment

class PresenceCheckRecyclerAdapter :
    RecyclerView.Adapter<PresenceCheckRecyclerAdapter.PresenceCheckViewHolder>() {

    inner class PresenceCheckViewHolder(val binding: ItemPresenceCheckBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var mMembersList = listOf<Member>()
    var attendanceList = arrayListOf<String>()
    var absenceList = HashMap<String, String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PresenceCheckViewHolder(
        ItemPresenceCheckBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PresenceCheckViewHolder, position: Int) {
        with(holder.binding) {
            val member = mMembersList[position]

            swipeItemPresenceLayout.close(false)
            root.tag = member.id
            memberName.text = member.name
            isPresentCheckBox.setOnCheckedChangeListener(null)
            isPresentCheckBox.isChecked = attendanceList.contains(member.id)

            Member.loadImage(memberPhoto, member)

            isPresentCheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    if (!attendanceList.contains(member.id)) {
                        attendanceList.add(member.id)
                        absenceList.remove(member.id)
                    }
                } else {
                    if (attendanceList.contains(member.id)) {
                        attendanceList.remove(member.id)
                    }
                }
                PresenceCheckFragment.listHasChanged = true
            }

            itemLayout.setOnClickListener {
                isPresentCheckBox.isChecked = !isPresentCheckBox.isChecked
                if (isPresentCheckBox.isChecked) {
                    if (!attendanceList.contains(member.id)) {
                        attendanceList.add(member.id)
                        absenceList.remove(member.id)
                    }
                } else {
                    if (attendanceList.contains(member.id)) {
                        attendanceList.remove(member.id)
                    }
                }
                PresenceCheckFragment.listHasChanged = true
            }

            absenceBtn.setOnClickListener { showAbsenceDialog(this, member.id, member.name) }
        }
    }

    override fun getItemCount(): Int = mMembersList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setLists(
        members: List<Member>, attendances: ArrayList<String>, absences: HashMap<String, String>
    ) {
        mMembersList = members
        attendanceList = attendances
        absenceList = absences
        notifyDataSetChanged()
    }

    private fun showAbsenceDialog(
        binding: ItemPresenceCheckBinding, memberId: String, memberName: String
    ) {
        with(binding) {
            val dialogBinding = DialogAbsenceBinding.inflate(LayoutInflater.from(root.context))
            dialogBinding.absenceReasonET.setText(absenceList[memberId])

            val dialog = AlertDialog.Builder(root.context)
                .setTitle(root.context.getString(R.string.absence_dialog_title, memberName))
                .setMessage(R.string.absence_dialog_message)
                .setView(dialogBinding.root)
                .setCancelable(false)
                .setPositiveButton(root.context.getString(R.string.save), null)
                .setNegativeButton(root.context.getString(R.string.cancel)) { dialog, _ -> dialog?.dismiss() }
                .create()

            dialog.setOnShowListener {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    val reason = dialogBinding.absenceReasonET.text.toString().trim()
                    if (reason.isEmpty()) {
                        dialogBinding.absenceReasonET.error =
                            root.context.getString(R.string.reason_empty_error)
                        return@setOnClickListener
                    } else {
                        absenceList[memberId] = reason
                        attendanceList.remove(memberId)
                        isPresentCheckBox.isChecked = false
                        dialog.dismiss()
                    }
                    PresenceCheckFragment.listHasChanged = true
                }
            }
            dialog.show()
        }
    }
}