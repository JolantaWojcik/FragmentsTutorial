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
    private int mIndex = -1;
    private int arrayLength;
    private static final String TAG = "Description";

    public int getIndex(){
        return mIndex;
    }

    public void showItemAtIndex(int newIndex){
        if(newIndex < 0 || newIndex >= arrayLength){
            return;
        }
        mIndex = newIndex;
        textView.setText(MainActivity.descriptionArray[mIndex]);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_description, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textView = (TextView) getActivity().findViewById(R.id.desc);
        arrayLength = MainActivity.descriptionArray.length;
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
