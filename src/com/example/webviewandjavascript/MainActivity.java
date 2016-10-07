package com.example.webviewandjavascript;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	private WebView mWebView;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mWebView = (WebView) findViewById(R.id.webView1);
		 WebSettings webSettings = mWebView.getSettings();
		 webSettings.setJavaScriptEnabled(true);
		 webSettings.setSupportZoom(true);
		 webSettings.setDisplayZoomControls(true);
		 webSettings.setBuiltInZoomControls(true);
		
		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onReceivedTitle(WebView view, String title) {
				// TODO Auto-generated method stub
				super.onReceivedTitle(view, title);

			}
		});
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				// view.loadUrl(url);

				return true;
			}
		});

		mWebView.addJavascriptInterface(new MyObject(), "demo");
		mWebView.loadUrl("file:///android_asset/index.html");

	}

	class MyObject {
		@JavascriptInterface
		public void clickOnAndroid() {
			mHandler.post(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					mWebView.loadUrl("javascript:myfun()");
				}
			});
		}
	}

}
