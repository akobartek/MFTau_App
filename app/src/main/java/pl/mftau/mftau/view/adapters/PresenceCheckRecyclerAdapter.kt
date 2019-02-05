package pl.mftau.mftau.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_absence.view.*
import kotlinx.android.synthetic.main.item_presence_check.view.*
import pl.mftau.mftau.R
import pl.mftau.mftau.model.Member
import pl.mftau.mftau.view.fragments.PresenceCheckFragment

class PresenceCheckRecyclerAdapter : RecyclerView.Adapter<PresenceCheckRecyclerAdapter.PresenceCheckViewHolder>() {

    private var mMembersList = listOf<Member>()
    var attendanceList = arrayListOf<String>()
    var absenceList = HashMap<String, String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PresenceCheckViewHolder =
            PresenceCheckViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_presence_check, parent, false))

    override fun onBindViewHolder(holder: PresenceCheckViewHolder, position: Int) =
            holder.bindView(mMembersList[position])

    override fun getItemCount(): Int = mMembersList.size

    fun setLists(memberList: List<Member>, attendanceList: ArrayList<String>, absenceList: HashMap<String, String>) {
        mMembersList = memberList
        this.attendanceList = attendanceList
        this.absenceList = absenceList
        notifyDataSetChanged()
    }

    inner class PresenceCheckViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(member: Member) {
            itemView.tag = member.id
            itemView.memberName.text = member.name
            itemView.isPresentCheckBox.setOnCheckedChangeListener(null)
            itemView.isPresentCheckBox.isChecked = attendanceList.contains(member.id)

            Member.loadImage(itemView.memberPhoto, member)

            itemView.isPresentCheckBox.setOnCheckedChangeListener { _, isChecked ->
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

            itemView.itemLayout.setOnClickListener {
                itemView.isPresentCheckBox.isChecked = !itemView.isPresentCheckBox.isChecked
                if (itemView.isPresentCheckBox.isChecked) {
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

            itemView.absenceBtn.setOnClickListener { showAbsenceDialog(member.id, member.name) }
        }

        @SuppressLint("InflateParams")
        private fun showAbsenceDialog(memberId: String, memberName: String) {
            val dialogView = LayoutInflater.from(itemView.context).inflate(R.layout.dialog_absence, null)
            dialogView.absenceReasonET.setText(absenceList[memberId])

            val dialog = AlertDialog.Builder(itemView.context)
                    .setTitle(itemView.context.getString(R.string.absence_dialog_title, memberName))
                    .setMessage(R.string.absence_dialog_message)
                    .setView(dialogView)
                    .setCancelable(false)
                    .setPositiveButton(itemView.context.getString(R.string.save), null)
                    .setNegativeButton(itemView.context.getString(R.string.cancel)) { dialog, _ -> dialog?.dismiss() }
                    .create()

            dialog.setOnShowListener {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    val reason = dialogView.absenceReasonET.text.toString().trim()
                    if (reason.isEmpty()) {
                        dialogView.absenceReasonET.error = itemView.context.getString(R.string.reason_empty_error)
                        return@setOnClickListener
                    } else {
                        absenceList[memberId] = reason
                        attendanceList.remove(memberId)
                        itemView.isPresentCheckBox.isChecked = false
                        dialog.dismiss()
                    }
                    PresenceCheckFragment.listHasChanged = true
                }
            }

            dialog.show()
        }
    }
}