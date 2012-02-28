package cc.wthr.mixup;

import cc.wthr.mixup.R;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.about);
		
		WebView webView = (WebView)findViewById(R.id.about_webview);
		webView.loadUrl("file:///android_asset/about/about.html");
	}
}
