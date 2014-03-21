package app.v3.paperly;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivity extends Activity { 
	private WebView myWebView;

	// @SuppressLint("JavascriptInterface")
	
	@Override   
	public void onCreate(Bundle savedInstanceState) {      
		super.onCreate(savedInstanceState); 
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		myWebView = (WebView) findViewById(R.id.webview);        
		WebSettings webSettings = myWebView.getSettings();      
		webSettings.setJavaScriptEnabled(true);      
	     
		myWebView.setWebViewClient(new WebViewClient());
		
		myWebView.loadUrl("http://mobile.paperly.de");
		if(!isOnline()){
			myWebView.addJavascriptInterface(new JavaScriptInterface(this), "Android");
			myWebView.loadUrl("file:///android_asset/internetErrorDefaultPage.html");
		}
	}	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
	        myWebView.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}
	
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	
	public WebView getwebview(){
		return myWebView;
	}
	
	@JavascriptInterface
	public void retryConnection(){
		runOnUiThread(new Runnable()
		  {
		    public void run()
		    {
		    	if(isOnline())
		    		myWebView.loadUrl("http://mobile.paperly.de");
		    }
		  });
		}
	}




	
