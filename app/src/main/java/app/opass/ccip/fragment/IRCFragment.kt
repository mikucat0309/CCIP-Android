package app.opass.ccip.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE
import androidx.fragment.app.Fragment
import app.opass.ccip.R
import app.opass.ccip.activity.MainActivity
import app.opass.ccip.network.webclient.OfficialWebViewClient
import app.opass.ccip.network.webclient.WebChromeViewClient
import app.opass.ccip.util.PreferenceUtil
import kotlinx.android.synthetic.main.fragment_web.*

class IRCFragment : Fragment() {
    companion object {
        private const val URL_NO_NETWORK = "file:///android_asset/no_network.html"
    }

    private lateinit var mActivity: MainActivity

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mActivity = requireActivity() as MainActivity

        return inflater.inflate(R.layout.fragment_web, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView.webViewClient = OfficialWebViewClient()
        webView.webChromeClient = WebChromeViewClient(progressBar)
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            if (Build.VERSION.SDK_INT >= 21) mixedContentMode = MIXED_CONTENT_COMPATIBILITY_MODE
        }
        webView.loadUrl(PreferenceUtil.getCurrentEvent(mActivity).features.irc)
    }
}
