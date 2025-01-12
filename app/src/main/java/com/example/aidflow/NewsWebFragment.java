package com.example.aidflow;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsWebFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsWebFragment extends Fragment {

    // Declaring the WebView
    private WebView myWebView;
    private Button btnBackNews;
    private static final String ARG_URL = "url_key";

    private String url;

    public NewsWebFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param url URL to load in the WebView
     * @return A new instance of fragment NewsWebFragment.
     */
    public static NewsWebFragment newInstance(String url) {
        NewsWebFragment fragment = new NewsWebFragment();
        Bundle args = new Bundle();
        args.putString(ARG_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(ARG_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_web, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Assigning the views to id's
        myWebView = view.findViewById(R.id.webview);

        btnBackNews = view.findViewById(R.id.btnBackNews);

        btnBackNews.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.destHome);
            }
        });

        // Checking if the URL is not null
        if (url != null) {
            // Enabling JavaScript for better web page experience
            myWebView.getSettings().setJavaScriptEnabled(true);

            // Ensuring links open within the WebView
            myWebView.setWebViewClient(new WebViewClient());

            // Loading and displaying the web page in the WebView
            myWebView.loadUrl(url);
        } else {
            // Handle the case where the URL is null (e.g., show an error message)
            // You can use Toast or Snackbar to show the error message to the user
            Toast.makeText(requireContext(), "URL is null", Toast.LENGTH_SHORT).show();
        }
    }
}