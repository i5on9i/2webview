package com.twoview;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.webkit.SslErrorHandler;
import android.net.http.SslError;


@SuppressLint("NewApi")
public class WebViewNewsFragment extends Fragment {
	
	private WebView mWebView;
	// Called when the Fragment is attached to its parent Activity.
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Get a reference to the parent Activity.
	}
	// Called to do the initial creation of the Fragment.
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Initialize the Fragment.
	}
	
	// Called once the Fragment has been created in order for it to
	// create its user interface.
	@Override
	public View onCreateView(LayoutInflater inflater, 
									ViewGroup container,
									Bundle savedInstanceState) {
		// Create, or inflate the Fragment's UI, and return it. 
		// If this Fragment has no UI then return null.
		
		
		return inflater.inflate(R.layout.fragment_webview, container, false);
	}
	
	// Called once the parent Activity and the Fragment's UI have 
	// been created.
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Complete the Fragment initialization ? particularly anything
		// that requires the parent Activity to be initialized or the 
		// Fragment's view to be fully inflated.
		mWebView = (WebView)getView();
		
		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		
		
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return false;
//	            if (Uri.parse(url).getHost().equals(URL)) {
//	            	
//	                // This is your web site, so do not override; let the WebView to load the page
//	                return false;
//	            }
//	            // Otherwise, the link is not for a page on my site, so launch another Activity that handles URLs
//	            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//	            startActivity(intent);
//	            return true;
	        }
	 
	        @Override
	        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
	            super.onReceivedSslError(view, handler, error);
	 
	            // this will ignore the Ssl error and will go forward to your site
	            handler.proceed();
	        }
		});
		
		mWebView.loadUrl("http://www.economist.com/printedition");
		
		
		// 2nd WebView
//		WebView wv = (WebView)(getActivity().findViewById(R.id.webview_newspaper));
//		wv.loadUrl("http://www.economist.com/printedition");
		
		
	}
	
	// Called at the start of the visible lifetime.
	@Override
	public void onStart(){
		super.onStart();
		// Apply any required UI change now that the Fragment is visible.
		//mWebView.loadUrl("http://www.economist.com/printedition");
		
	}
	
	// Called at the start of the active lifetime.
	@Override
	public void onResume(){
		super.onResume();
		// Resume any paused UI updates, threads, or processes required
		// by the Fragment but suspended when it became inactive.
	}
	// Called at the end of the active lifetime.
	@Override
	public void onPause(){
		// Suspend UI updates, threads, or CPU intensive processes
		// that don't need to be updated when the Activity isn't
		// the active foreground activity.
		// Persist all edits or state changes
		// as after this call the process is likely to be killed.
		super.onPause();
	}
	
	// Called to save UI state changes at the
	// end of the active lifecycle.
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		// Save UI state changes to the savedInstanceState.
		// This bundle will be passed to onCreate, onCreateView, and
		// onCreateView if the parent Activity is killed and restarted.
		super.onSaveInstanceState(savedInstanceState);
	}
	
	// Called at the end of the visible lifetime.
	@Override
	public void onStop(){
		// Suspend remaining UI updates, threads, or processing
		// that aren't required when the Fragment isn't visible.
		super.onStop();
	}
	
	// Called when the Fragment's View has been detached.
	@Override
	public void onDestroyView() {
		// Clean up resources related to the View.
		super.onDestroyView();
	}
	
	// Called at the end of the full lifetime.
	@Override
	public void onDestroy(){
		// Clean up any resources including ending threads,
		// closing database connections etc.
		super.onDestroy();
	}
	
	// Called when the Fragment has been detached from its parent Activity.
	@Override
	public void onDetach() {
		super.onDetach();
	}
}