package es.energysistem.manual;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;

public class MainActivity extends Activity {

    private enum Idioma {
        ES, EN, FR, PT;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isOnline()) {
            WebView webView = (WebView) findViewById(R.id.webView);
            // Enable JavaScript
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
            try {
                Idioma idioma = Idioma.valueOf(Locale.getDefault().getCountry());
                Toast.makeText(this, idioma.toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(this,Locale.getDefault().getCountry(), Toast.LENGTH_SHORT).show();

                switch (idioma) {
                    case ES:
                        webView.loadUrl("http://www.energysistem.com/guide/39530");
                        break;
                    case EN:
                        webView.loadUrl("http://www.energysistem.com/en/guide/39530");
                        break;
                    case PT:
                        webView.loadUrl("http://www.energysistem.com/pt/guide/39530");
                        break;
                    case FR:
                        webView.loadUrl("http://www.energysistem.com/fr/guide/39530");
                        break;

                    default:
                        webView.loadUrl("http://www.energysistem.com/en/guide/39530");
                        break;
                }
            } catch (Exception error) {
                webView.clearCache(true);
                webView.loadUrl("http://www.energysistem.com/en/guide/39530");
            }
        }
        else
        {
            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.AlertTitle))
                    .setMessage(getResources().getString(R.string.textAlert))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            closeActivity();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }
    private void closeActivity()
    {
        this.finish();
    }
    public boolean isOnline() {
        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }


}
