package com.example.jola.tutorialfragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Description extends Fragment {

    private TextView textView;
    private int arrayLength;
    private static final String TAG = "Description";

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        textView = (TextView) view.findViewById(R.id.desc);
        textView.setText(MainActivity.descriptionArray[getShownIndex()]);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(TAG, getClass().getSimpleName() + "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

    //makes the fragment visible to the user (based on its containing activity being started)
    @Override
    public void onStart() {
        Log.i(TAG, getClass().getSimpleName() + "onStart()");
        super.onStart();
    }

    //makes the fragment begin interacting with the user (based on its containing activity being resumed)
    @Override
    public void onResume() {
        Log.i(TAG, getClass().getSimpleName() + "onResume()");
        super.onResume();
    }
}
