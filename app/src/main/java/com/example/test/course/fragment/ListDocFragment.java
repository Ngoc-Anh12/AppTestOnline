package com.example.test.course.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.R;

public class ListDocFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final Bundle bundle=getArguments();
        int subjectId=bundle.getInt("courseId-fragment");
        return inflater.inflate(R.layout.fragment_list_doc, container, false);
    }
}