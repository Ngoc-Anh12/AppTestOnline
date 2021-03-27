package com.example.test.course.fragment;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.ViewUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
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
    MediaController mediaController;
    boolean isVisible = false;
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

             //String getUrlVideo = chapterList.get(position).getUnit().get(position).getDocument().get(position).getDocumentOfUnitName();
             if (chapterList.get(position).getUnit().get(position).getDocument().get(position).getDocumentOfUnitName() == ""){
                 Toast.makeText(getContext(),"Link error",Toast.LENGTH_LONG).show();
             }else {
                 videoView.setVisibility(View.VISIBLE);
                 String url = "http://192.168.1.140:5000/" + chapterList.get(position).getUnit().get(position).getDocument().get(position).getDocumentOfUnitName().replaceAll("\\s", "") + "?authorization=abc";
                 uri = Uri.parse("" + url);
                 try {
                    videoView.setVideoURI(uri);
                    videoView.requestFocus();
                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                                @Override
                                public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                                    mediaController = new MediaController(getContext());
                                    videoView.setMediaController(mediaController);
                                    mediaController.setAnchorView(videoView);
                                }
                            });
                            mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                                @Override
                                public boolean onInfo(MediaPlayer mp, int what, int extra) {
                                    switch (what) {
                                        case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START: {
                                          //  progressBar.setVisibility(View.GONE);
                                            return true;
                                        }
                                        case MediaPlayer.MEDIA_INFO_BUFFERING_START: {
                                           // progressBar.setVisibility(View.VISIBLE);
                                            return true;
                                        }
                                        case MediaPlayer.MEDIA_INFO_BUFFERING_END: {
                                          //  progressBar.setVisibility(View.GONE);
                                            return true;
                                        }
                                        case MediaPlayer.MEDIA_INFO_NOT_SEEKABLE: {
                                            Log.v("","Media Info, Media Info Not Seekable " + extra);
                                          //  progressBar.setVisibility(View.GONE);
                                            return true;
                                        }
                                    }

                                    return false;
                                }
                            });
                            if (isVisible){
                                mp.start();
                            }
                        }
                    });
                 }catch (Exception e){
                     Log.e("VIDEO PLAYER ERROR ", e.getMessage());
                 }
                 videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                     @Override
                     public void onCompletion(MediaPlayer mp) {
                         videoView.seekTo(0);
                     }
                 });
                 videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                     @Override
                     public boolean onError(MediaPlayer mp, int what, int extra) {
                         Toast.makeText(getContext(), "Oops An Error Occur While Playing Video...!!!", Toast.LENGTH_LONG).show();
                         Log.d("Video View Error", Integer.toString(what));
                         String errorString = "Media Player Error: ";
                         switch (what) {
                             case MediaPlayer.MEDIA_ERROR_UNKNOWN: {
                                 // errorString += "Unspecified media player error. ";
                                 Toast.makeText(getContext(),"Video Error, Error Unknown",Toast.LENGTH_SHORT).show();
                                 Log.v(errorString,"Media Error, Error Unknown " + extra);
                             }
                             case MediaPlayer.MEDIA_ERROR_SERVER_DIED: {
                                 // errorString += "Media server died. ";
                                 Log.v(errorString,"Media Error, Server Died " + extra);
                                 Toast.makeText(getContext(),"Check internet",Toast.LENGTH_SHORT).show();
                             }
                         }

                         switch (extra) {
                             case MediaPlayer.MEDIA_ERROR_MALFORMED: {
                                 errorString += "Bitstream is not conforming to the related coding standard or file spec.";
                             }
                             case MediaPlayer.MEDIA_ERROR_UNSUPPORTED: {
                                 errorString += "Bitstream is conforming to the related coding standard or file spec, but the media framework does not support the feature.";
                             }
                             case MediaPlayer.MEDIA_ERROR_TIMED_OUT: {
                                 errorString += "Media operation timed out.";
                             }
                         }
                         Log.d("Error about video", errorString);
                         return true;
                     }
                 });
             }
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
        return view;
    }

    @Override
    public void onStop() {
        videoView.setVisibility(View.GONE);
        super.onStop();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        isVisible = isVisibleToUser;
        if(videoView!=null)
            videoView.pause();
        super.setUserVisibleHint(isVisibleToUser);
    }
}