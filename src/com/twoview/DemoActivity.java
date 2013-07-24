package com.twoview;




import com.twoview.SlidingUpPanelLayout.PanelSlideListener;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class DemoActivity extends Activity {

	private String mMainUrl = "http://www.economist.com/printedition";
	private String mSubUrl = "http://m.endic.naver.com/";
	
	private WebView mMainWebView;
	private WebView mSubWebView;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_demo);

        SlidingUpPanelLayout layout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        layout.setShadowDrawable(getResources().getDrawable(R.drawable.above_shadow));
        layout.setPanelSlideListener(new PanelSlideListener() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
			@Override
            public void onPanelSlide(View panel, float slideOffset) {
                if (slideOffset < 0.2) {
                    if (getActionBar().isShowing()) {
                        getActionBar().hide();
                    }
                } else {
                    if (!getActionBar().isShowing()) {
                        getActionBar().show();
                    }
                }
            }

            @Override
            public void onPanelExpanded(View panel) {
            	

            }

            @Override
            public void onPanelCollapsed(View panel) {


            }
        });
        TextView t = (TextView) findViewById(R.id.brought_by);
        t.setMovementMethod(LinkMovementMethod.getInstance());
        
        
        // main panel
        mMainWebView = (WebView) findViewById(R.id.webview1);
		
		WebSettings webSettings = mMainWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		
		mMainWebView.setWebViewClient(new MyWebViewClient());
		
		mMainWebView.loadUrl(mMainUrl);
		
		// Cookie policy - use no cookie
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.setAcceptCookie(false);


		// sliding panel
		mSubWebView = (WebView) findViewById(R.id.webview2);
        		
		webSettings = mSubWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		
		mSubWebView.setWebViewClient(new MyWebViewClient());
		
		mSubWebView.loadUrl(mSubUrl);
		
    }

    @Override
    public void onBackPressed() {
    	if (mMainWebView.isFocused() && mMainWebView.canGoBack()) {
    		mMainWebView.goBack();       
	    }
	    else {
            super.onBackPressed();
	    }
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.demo, menu);
        return true;
    }
    
    ////////////////////////////////////////////////
    ////
    ////    private MyWebViewClient 
    ////
    private class MyWebViewClient extends WebViewClient {
    	@Override
    	public boolean shouldOverrideUrlLoading(WebView view, String url) {
    		// Invoked when new url is about to load.
    		
    		// This is my web site, so do not override; let my WebView load the page
    		Log.d("namh", url);
    		// view.loadUrl(url);
            return false; // then it is not handled by default action

    	}

    	@Override
    	public void onPageStarted(WebView view, String url, Bitmap favicon) {

//    		if(url.startsWith(PocketVariables.REDIRECT_URL)){
//
//    			Log.d("namh", "login has been finished");
//
//    		}
    	}
    	
    	 @Override
    	 public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
    		 super.onReceivedSslError(view, handler, error);

    		 // this will ignore the Ssl error and will go forward to your site
    		 handler.proceed();
    	 }


    }

}
