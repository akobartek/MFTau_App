package pl.mftau.mftau.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.ArrayList

object PermissionUtils {

    fun requestCalendarReadWritePermission(sourceActivity: Activity) {
        val permissionList = ArrayList<String>()

        if (ContextCompat.checkSelfPermission(sourceActivity,
                        Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_CALENDAR)
        }

        if (ContextCompat.checkSelfPermission(sourceActivity,
                        Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_CALENDAR)
        }

        if (permissionList.size > 0) {
            val permissionArray = arrayOfNulls<String>(permissionList.size)

            for (i in 0 until permissionList.size) {
                permissionArray[i] = permissionList[i]
            }

            ActivityCompat.requestPermissions(sourceActivity, permissionArray, 911)
        }
    }

    fun haveCalendarReadWritePermissions(sourceActivity: Activity): Boolean {
        var permissionCheck = ContextCompat.checkSelfPermission(sourceActivity,
                Manifest.permission.READ_CALENDAR)

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            permissionCheck = ContextCompat.checkSelfPermission(sourceActivity,
                    Manifest.permission.WRITE_CALENDAR)

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                return true
            }
        }
        return false
    }
}