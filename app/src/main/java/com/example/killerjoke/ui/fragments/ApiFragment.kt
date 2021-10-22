package com.example.killerjoke.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.* // ktlint-disable no-wildcard-imports
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.killerjoke.databinding.FragmentApiBinding
import com.example.killerjoke.other.Constants.WEB_URL

class ApiFragment : Fragment() {

    private var _binding: FragmentApiBinding? = null
    private val binding get() = _binding!!

    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = binding.webViewApi
        webView.webViewClient = WebViewClient()

        if (savedInstanceState != null)
            webView.restoreState(savedInstanceState)
        else
            webViewUpdate()
        onBackPress()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun webViewUpdate() {
        webView.apply {
            loadUrl(WEB_URL)
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
            settings.builtInZoomControls = true
            settings.setSupportZoom(true)
        }
    }

    private fun onBackPress() {
        webView.canGoBack()
        webView.setOnKeyListener(
            View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK &&
                    event.action == MotionEvent.ACTION_UP &&
                    webView.canGoBack()
                ) {
                    webView.goBack()
                    return@OnKeyListener true
                }
                false
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
