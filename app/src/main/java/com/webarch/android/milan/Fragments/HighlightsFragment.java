package com.webarch.android.milan.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webarch.android.milan.EventAdapter;
import com.webarch.android.milan.R;

/**
 * Created by ajitesh on 11/2/16.
 */
public class HighlightsFragment extends Fragment {
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_highlights,container,false);
        return view;
    }

}
