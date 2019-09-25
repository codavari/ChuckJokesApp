package ru.slavicsky.chuckjokesapp.view.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.slavicsky.chuckjokesapp.R


class WebFragment : Fragment() {
    companion object {
        private const val API_URL = "http://www.icndb.com/api/"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar!!.title = "Api info"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_web, container, false)

        val mWebView = root.findViewById(R.id.web_view) as WebView
        mWebView.settings.javaScriptEnabled = true
        mWebView.webViewClient = WebViewClient()
        mWebView.loadUrl(API_URL)
        return root
    }


}