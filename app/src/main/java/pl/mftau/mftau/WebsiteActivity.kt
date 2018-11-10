package pl.mftau.mftau

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_website.*
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Build
import android.view.View
import android.webkit.WebView


class WebsiteActivity : AppCompatActivity() {

    companion object {
        const val urlAddress = "http://mftau.pl/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_website)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            window.statusBarColor = Color.WHITE
        }

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                if (url == urlAddress)
                    loadingIndicator.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageCommitVisible(view: WebView?, url: String?) {
                loadingIndicator.visibility = View.GONE
                super.onPageCommitVisible(view, url)
            }
        }
        webView.loadUrl(urlAddress)
    }
}
