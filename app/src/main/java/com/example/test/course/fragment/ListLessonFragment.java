package com.example.test.course.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.R;
import com.example.test.core.retrofit.ApiClient;
import com.example.test.core.retrofit.RequestApi;
import com.example.test.course.adapter.ChapterAdapter;
import com.example.test.model.Chapter;
import com.example.test.model.ResponseDTO;
import com.example.test.utils.DataServices;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListLessonFragment extends Fragment {
    RecyclerView recyclerView;
    List<Chapter> chapterList;
    final RequestApi requestAPI = ApiClient.getClient().create(RequestApi.class);
    ChapterAdapter chapterAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_lesson, container, false);
        recyclerView = view.findViewById(R.id.recycle_view_list_course);
        chapterList = new ArrayList<>();
        chapterAdapter = new ChapterAdapter();
        final Bundle bundle=getArguments();
        int subjectId=bundle.getInt("courseId-fragment");
        String token  = DataServices.getInstance(getContext()).getToken();
        requestAPI.getChapter(15, subjectId).enqueue(new Callback<ResponseDTO<List<Chapter>>>() {
            @Override
            public void onResponse(Call<ResponseDTO<List<Chapter>>> call, Response<ResponseDTO<List<Chapter>>> response) {
                if(response.body() != null && response.body().data != null && response.body().error==0){
                    System.out.println(response.body().data);
                    /*for (Chapter chapter : response.body().data){
                        chapterList.add(chapter);
                        chapterAdapter.setData(chapterList, getContext());
                        recyclerView.setAdapter(chapterAdapter);
                    }*/
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO<List<Chapter>>> call, Throwable t) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
       // callAPI();
        return view;
    }

    private void callAPI() {

    }
}