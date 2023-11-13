package pl.mftau.mftau.core.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData

@Composable
fun ComposeWebView(html: String) {
    val webViewState = rememberWebViewStateWithHTMLData(data = html)
    WebView(
        state = webViewState,
        captureBackPresses = false,
        modifier = Modifier.fillMaxSize()
    )
}