package pl.mftau.mftau.android

import android.Manifest
import android.animation.ObjectAnimator
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.Navigator
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.KoinContext
import pl.mftau.mftau.R
import pl.mftau.mftau.breviary.presentation.BreviarySelectScreen
import pl.mftau.mftau.core.presentation.screens.MainScreen
import pl.mftau.mftau.common.utils.safePush
import pl.mftau.mftau.songbook.presentation.screens.SongBookListScreen
import pl.mftau.mftau.ui.CustomTransition
import pl.mftau.mftau.ui.theme.MFTauTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setOnExitAnimationListener { screen ->
                try {
                    val zoomX = ObjectAnimator.ofFloat(screen.iconView, View.SCALE_X, 0.5f, 0.0f)
                    zoomX.duration = 400L
                    zoomX.doOnEnd {
                        screen.remove()
                        viewModel.splashScreenEnded()
                    }

                    val zoomY = ObjectAnimator.ofFloat(screen.iconView, View.SCALE_Y, 0.5f, 0.0f)
                    zoomY.duration = 400L
                    zoomY.doOnEnd {
                        screen.remove()
                        viewModel.splashScreenEnded()
                    }

                    zoomX.start()
                    zoomY.start()
                } catch (exc: NullPointerException) {
                    screen.remove()
                    viewModel.splashScreenEnded()
                }
            }
        }

        var shortcut = intent.getStringExtra("shortcut")
        intent.putExtra("shortcut", "")

        setContent {
            val preferences by viewModel.preferences.collectAsStateWithLifecycle()
            val splashScreenEnded by viewModel.splashScreenEnded.collectAsStateWithLifecycle()

            if (!preferences.notificationsAsked)
                askNotificationPermission()

            KoinContext {
                MFTauTheme(
                    colorTheme = preferences.colorTheme,
                    dynamicColor = preferences.dynamicColors,
                    splashScreenEnded = splashScreenEnded
                ) {
                    val color = MaterialTheme.colorScheme.primary
                    LaunchedEffect(key1 = preferences) {
                        viewModel.updateAccentColor(color)
                        setKeepScreenAwakeWindowFlag(preferences.keepScreenAwake)
                    }

                    Surface(color = MaterialTheme.colorScheme.background) {
                        Navigator(screen = MainScreen()) { navigator ->
                            when (shortcut) {
                                "songBook" -> navigator.safePush(SongBookListScreen())
                                "breviary" -> navigator.safePush(BreviarySelectScreen())
                            }
                            shortcut = null
                            CustomTransition(navigator = navigator)
                        }
                    }
                }
            }
        }
    }

    private fun setKeepScreenAwakeWindowFlag(keepAwake: Boolean) {
        if (keepAwake) window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        else window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun askNotificationPermission() {
        Firebase.messaging.subscribeToTopic("all")
        viewModel.updateNotificationsAsked(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
                && !shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)
            ) {
                MaterialAlertDialogBuilder(this)
                    .setIcon(R.drawable.logo_color)
                    .setTitle(R.string.notification_dialog_title)
                    .setMessage(R.string.notification_ask_dialog_message)
                    .setCancelable(false)
                    .setPositiveButton(R.string.yes) { dialog, _ ->
                        dialog.dismiss()
                        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                    }
                    .setNegativeButton(R.string.no) { dialog, _ -> dialog.dismiss() }
                    .create()
                    .show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (!isGranted) {
                if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS))
                    MaterialAlertDialogBuilder(this)
                        .setIcon(R.drawable.logo_color)
                        .setTitle(R.string.notification_dialog_title)
                        .setMessage(R.string.notification_denied_dialog_message)
                        .setCancelable(false)
                        .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
                        .create()
                        .show()
            }
        }
}