package com.example.jola.tutorialfragments;

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

        nameLayout = (FrameLayout) findViewById(R.id.name);
        descLayout = (FrameLayout) findViewById(R.id.desc);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.name, new PhilosophName());
        fragmentTransaction.commit();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                setLayout();
            }
        });
    }

    public void setLayout(){
        if(!description.isAdded()){
            nameLayout.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT));
            descLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT));
        }else{
            nameLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 1f));
            descLayout.setLayoutParams(new LinearLayout.LayoutParams(0, MATCH_PARENT, 2f));
        }
    }

    public void onListSelection(int index){
        if(!description.isAdded()){
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.desc, new Description());
            //back to one fragment layout
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            //force transaction to be execute imediatelly
            fragmentManager.executePendingTransactions();
        }
        if(description.getIndex() != index){
            description.showItemAtIndex(index);
        }
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, getClass().getSimpleName() + "onDestroy()");
        super.onDestroy();
    }
}
