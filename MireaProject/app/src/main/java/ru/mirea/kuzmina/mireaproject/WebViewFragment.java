package ru.mirea.kuzmina.mireaproject;
import android.os.Bundle;
import android.annotation.SuppressLint;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class WebViewFragment extends Fragment {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_web_view, container, false);
        WebView webView = view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                Log.e("WebView", "Ошибка загрузки страницы: " + error.getDescription());
            }
        });
        webView.loadUrl("https://www.google.com");

        return view;
    }
}