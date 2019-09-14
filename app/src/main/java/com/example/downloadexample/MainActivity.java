package com.example.downloadexample;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.downloadexample.adapter.MyAdapter;
import com.example.downloadexample.response.MyResponse;
import com.example.downloadexample.retrofit.ApiClient;
import com.example.downloadexample.retrofit.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ClickEvent {

    private ArrayList<MyResponse.Data> historyList = new ArrayList<>();
    private RecyclerView historyRecyclerview;
    private MyAdapter historyAdapter;
    String root = "";
    String mime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        historyRecyclerview = findViewById(R.id.myRv);


        final ApiInterface api = ApiClient.getApiService();
        Call<MyResponse> call = api.addRequest(1);

        call.enqueue(new Callback<MyResponse>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<MyResponse> call, @NonNull Response<MyResponse> response) {
                if (response.code() == 200) {
                    if (response.body() != null) {
                        historyList = response.body().getData();
                        root = response.body().getDocument_path();
                        historyAdapter = new MyAdapter(historyList, MainActivity.this);
                        historyRecyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayout.VERTICAL, false));
                        historyRecyclerview.setHasFixedSize(true);
                        historyRecyclerview.setItemAnimator(new DefaultItemAnimator());
                        historyRecyclerview.setAdapter(historyAdapter);
                        historyAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                Log.e("onFailure", "" + t.toString());

                Toast.makeText(MainActivity.this, "" + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void myClickEvent(String docURL) {

        mime = getMimeType(docURL);

        Toast.makeText(this, "" + docURL, Toast.LENGTH_SHORT).show();
        download(docURL);

    }

    public void download(String url) {
        Uri uri = Uri.parse(root + url);
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
                DownloadManager.Request.NETWORK_MOBILE);
        // set title and description
        request.setTitle("Data Download");
        request.setDescription("Android Data download using DownloadManager.");

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        //set the local destination for download file to a path within the application's external files directory
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, url);
        request.setMimeType(mime);
        downloadManager.enqueue(request);
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }


}
