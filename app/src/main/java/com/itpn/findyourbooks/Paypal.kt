package com.itpn.findyourbooks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView

class Paypal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paypal)

        val miWebView:WebView = findViewById(R.id.webViewPaypal)

        val websettings:WebSettings = miWebView.settings
        websettings.javaScriptEnabled = true
        miWebView.webChromeClient = WebChromeClient()

        miWebView.loadUrl("file:///android_asset/paypal.html")
    }
}