package pl.mftau.mftau.providers

import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import androidx.core.graphics.drawable.IconCompat
import androidx.slice.Slice
import androidx.slice.SliceProvider
import androidx.slice.builders.ListBuilder.INFINITY
import androidx.slice.builders.SliceAction
import androidx.slice.builders.list
import androidx.slice.builders.row
import androidx.slice.core.SliceHints
import pl.mftau.mftau.R
import pl.mftau.mftau.view.activities.MainActivity

class MySliceProvider : SliceProvider() {

    override fun onCreateSliceProvider(): Boolean {
        return true
    }

    override fun onMapIntentToUri(intent: Intent?): Uri {
        return super.onMapIntentToUri(intent)
    }

    override fun onBindSlice(sliceUri: Uri): Slice? {
        when (sliceUri.path) {
            "/breviary" -> return createBreviarySlice(sliceUri)
        }
        return null
    }

    private fun createBreviarySlice(sliceUri: Uri): Slice? {
        return list(context!!, sliceUri, INFINITY) {
            row {
                title = context!!.getString(R.string.breviary)
                subtitle = context!!.getString(R.string.slice_breviary_subtitle)
                primaryAction = createBreviaryAction()
            }
        }
    }

    private fun createBreviaryAction(): SliceAction {
        return SliceAction.create(
            PendingIntent.getActivity(context, 0,
                Intent(context, MainActivity::class.java).apply { putExtra("shortcut", "breviary") }, 0
            ),
            IconCompat.createWithResource(context, R.drawable.shortcut_ic_breviary),
            SliceHints.ICON_IMAGE,
            "Open Breviary."
        )
    }
}