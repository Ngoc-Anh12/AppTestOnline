package com.example.test.document;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.core.retrofit.ApiClient;
import com.example.test.core.retrofit.RequestApi;
import com.example.test.model.Document;
import com.example.test.model.Down;
import com.example.test.model.ResponseDTO;
import com.example.test.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentActivity extends AppCompatActivity {
    RecyclerView recycler_down;
    final RequestApi requestAPI = ApiClient.getClient().create(RequestApi.class);
    AdapterDown adapterDown;
    List<Document> downList;
    int REQUEST_PERMISSION_CODE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        recycler_down =findViewById(R.id.recycler_down);
        adapterDown = new AdapterDown();
        downList = new ArrayList<>();
        recycler_down.setHasFixedSize(true);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycler_down.setLayoutManager(linearLayoutManager);
        requestAPI.getListDown().enqueue(new Callback<ResponseDTO<List<Document>>>() {
            @Override
            public void onResponse(Call<ResponseDTO<List<Document>>> call, Response<ResponseDTO<List<Document>>> response) {
                if(response.body() != null && response.body().data != null && response.body().error==0){
                    for (Document document : response.body().data){
                        downList.add(document);
                        adapterDown.setData(downList, DocumentActivity.this);
                        recycler_down.setAdapter(adapterDown);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<List<Document>>> call, Throwable t) {

            }
        });


        recycler_down.addOnItemTouchListener(new RecyclerItemClickListener(this, recycler_down, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                 {
                    String url = "https://www.cambridgeenglish.org/images/84669-pet-vocabulary-list.pdf";
                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
                    request.setTitle("Download");
                    request.setDescription("Download file ...");
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, String.valueOf(System.currentTimeMillis()));
                    DownloadManager downloadManager =  (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE) ;
                    if (downloadManager != null){
                        downloadManager.enqueue(request);
                    }
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }



}