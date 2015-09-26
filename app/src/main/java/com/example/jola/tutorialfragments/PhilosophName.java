package com.example.jola.tutorialfragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class PhilosophName extends ListFragment {

    public static final String TAG = "PhilosophName";
    private ListSelcetionListener mListener = null;
    private int mIndex = -1;

    public interface ListSelcetionListener{
        public void onListSelection(int index);
    }

    //called once the fragment is associated with its activity
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (ListSelcetionListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
            + "must implement ... ");
        }
    }

    //called to do initial creation of the fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Control whether a fragment instance is retained across Activity re-creation (such as from a configuration change)
        setRetainInstance(true);
    }

   // creates and returns the view hierarchy associated with the fragment
    //use own xml layout
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //  View view = inflater.inflate(R.layout.fragment_item, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //tells the fragment that its activity has completed its own Activity.onCreate()
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.fragment_item_list, MainActivity.nameArray));

        if(-1 != mIndex){
            getListView().setItemChecked(mIndex, true);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(mIndex != position){
            mIndex = position;
            mListener.onListSelection(position);
        }
    }

    //called immediately prior to the fragment no longer being associated with its activity
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
