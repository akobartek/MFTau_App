package pl.mftau.mftau.view.fragments


import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.navigation.fragment.findNavController
import pl.mftau.mftau.databinding.FragmentWebsiteBinding

class WebsiteFragment : BindingFragment<FragmentWebsiteBinding>() {

    override fun attachBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentWebsiteBinding.inflate(inflater, container, false)

    @SuppressLint("SetJavaScriptEnabled")
    override fun setup(savedInstanceState: Bundle?) {
        val urlAddress = WebsiteFragmentArgs.fromBundle(requireArguments()).websiteAddress
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(webView: WebView, url: String, favicon: Bitmap?) {
                if (url == urlAddress) binding.loadingIndicator.show()
                super.onPageStarted(webView, url, favicon)
            }

            override fun onPageCommitVisible(webView: WebView?, url: String?) {
                binding.loadingIndicator.hide()
                super.onPageCommitVisible(webView, url)
            }
        }

        if (savedInstanceState != null) binding.webView.restoreState(savedInstanceState)
        else binding.webView.loadUrl(urlAddress)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.webView.saveState(outState)
    }

    fun onBackPressed(): Boolean {
        return if (binding.webView.canGoBack()) {
            binding.webView.goBack()
            false
        } else {
            findNavController().navigateUp()
            true
        }
    }
}
