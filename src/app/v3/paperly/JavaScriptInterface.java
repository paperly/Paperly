package app.v3.paperly;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class JavaScriptInterface {
MainActivity mActivity;

/** Instantiate the interface and set the context */
JavaScriptInterface(MainActivity m) {
    mActivity = m;
}

/** Show a toast from the web page */
@JavascriptInterface
public void retryConnection() {
    
    mActivity.retryConnection();
}
}
