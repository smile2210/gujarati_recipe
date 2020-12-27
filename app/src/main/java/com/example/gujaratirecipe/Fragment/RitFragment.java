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


public class RitFragment extends Fragment {
    TextView rittext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_rit, container, false);
        rittext = view.findViewById(R.id.rittext);
        rittext.setText(ThirdActivity.rit);

        return view;
    }
}