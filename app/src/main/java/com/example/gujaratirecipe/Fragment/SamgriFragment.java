package com.example.gujaratirecipe.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gujaratirecipe.MainAdapter;
import com.example.gujaratirecipe.R;
import com.example.gujaratirecipe.ThirdActivity;

public class SamgriFragment extends Fragment {

    TextView samgritext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_samgri, container, false);
        samgritext = view.findViewById(R.id.samgritext);
        samgritext.setText(ThirdActivity.samgri);
        return view;
    }
}