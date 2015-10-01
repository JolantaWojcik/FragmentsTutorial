package com.example.jola.tutorialfragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    /*
    -> The back stack allows the user to reverse a fragment transaction (navigate backwards), by pressing the Back button.
    -> When you add a fragment as a part of your activity layout, it lives in a ViewGroup inside the activity's view hierarchy
    and the fragment defines its own view layout.
     */

    public static String[] nameArray;
    public static String[] descriptionArray;
    private Description description = new Description();
    private FragmentManager fragmentManager;
    private FrameLayout nameLayout, descLayout;
    private static final int MATCH_PARENT = LinearLayout.LayoutParams.MATCH_PARENT;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameArray = getResources().getStringArray(R.array.names);
        descriptionArray = getResources().getStringArray(R.array.description);
    }
}
