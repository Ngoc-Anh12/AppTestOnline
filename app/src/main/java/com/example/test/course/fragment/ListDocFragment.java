package com.example.test.course.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.ViewUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.test.R;
import com.example.test.core.retrofit.ApiClient;
import com.example.test.core.retrofit.RequestApi;
import com.example.test.course.adapter.ChapterAdapter;
import com.example.test.course.adapter.UnitChapter;
import com.example.test.course.adapter.VideoAdapter;
import com.example.test.model.Chapter;
import com.example.test.model.Document;
import com.example.test.model.ResponseDTO;
import com.example.test.model.Unit;
import com.example.test.utils.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListDocFragment extends Fragment {
    RecyclerView recyclerView;
    List<Chapter> chapterList;
    final RequestApi requestAPI = ApiClient.getClient().create(RequestApi.class);
    VideoAdapter videoAdapter;
    List<Document> docList;
    List<Unit> unitList;
    VideoView videoView;
    Uri uri;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Bundle bundle=getArguments();
        int subjectId=bundle.getInt("courseId-fragment");
        View view = inflater.inflate(R.layout.fragment_list_doc, container, false);
        chapterList = new ArrayList<>();
        docList = new ArrayList<>();
        unitList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recycler_view_video);
        videoAdapter = new VideoAdapter();
        videoView = view.findViewById(R.id.video_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        requestAPI.getChapter(15, 2).enqueue(new Callback<ResponseDTO<List<Chapter>>>() {
            @Override
            public void onResponse(Call<ResponseDTO<List<Chapter>>> call, Response<ResponseDTO<List<Chapter>>> response) {
                if(response.body() != null && response.body().data != null && response.body().error==0){
                    System.out.println(response.body().data);
                    for (Chapter chapter : response.body().data){
                        chapterList.add(chapter);
                        videoAdapter.setData(chapterList, getContext());
                        recyclerView.setAdapter(videoAdapter);

                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<List<Chapter>>> call, Throwable t) {

            }
        });
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
             String getUrlVideo = chapterList.get(position).getUnit().get(position).getDocument().get(position).getDocumentOfUnitName();
             if (getUrlVideo == ""){
                 Toast.makeText(getContext(),"Link error",Toast.LENGTH_LONG).show();
             }else {
                 videoView.setVisibility(View.VISIBLE);
                 String url = "http://192.168.2.213:5000" + getUrlVideo.replaceAll("\\s", "") + "?authorization=abc";
                 uri = Uri.parse("" + url);
             }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        return view;
    }
}