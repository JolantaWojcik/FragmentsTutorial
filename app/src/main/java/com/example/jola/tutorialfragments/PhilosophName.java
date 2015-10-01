package com.example.jola.tutorialfragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PhilosophName extends ListFragment {
    /*
    ListFragment:
    Displays a list of items that are managed by an adapter (such as a SimpleCursorAdapter),
    similar to ListActivity. It provides several methods for managing a list view,
    such as the onListItemClick() callback to handle click events.
     */

    public static final String TAG = "PhilosophName";
    private ListSelcetionListener mListener;

    boolean mDualPane;
    int mIndex = 0;

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
            + "must implement ListSelcetionListener ");
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
        //A boolean indicating whether the inflated layout should be attached to the ViewGroup
       // View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //tells the fragment that its activity has completed its own Activity.onCreate()
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Populate list with our static array of names
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, MainActivity.nameArray));

        //enables filtering for the contents of the given ListView
        getListView().setTextFilterEnabled(true);

        // Check to see if we have a frame in which to embed the details
        // fragment directly in the containing UI.
        // R.id.details relates to the res/layout-land/fragment_layout.xml
        // This is first created when the phone is switched to landscape
        // mode
        View detailsLandMode = getActivity().findViewById(R.id.details);
        mDualPane = detailsLandMode != null && detailsLandMode.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            // Restore last state for checked position.
            mIndex = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDualPane) {
            // In dual-pane mode, the list view highlights the selected item.
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            // Make sure our UI is in the correct state.
            showDetails(mIndex);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice", mIndex);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showDetails(position);
    }


    private void showDetails(int index) {
        mIndex = index;
        // The basic design is mutli-pane (landscape on the phone) allows us
        // to display both fragments (titles and details) with in the same
        // activity; that is FragmentLayout -- one activity with two
        // fragments.
        // Else, it's single-pane (portrait on the phone) and we fire
        // another activity to render the details fragment - two activities
        // each with its own fragment .
        //
        if (mDualPane) {
            // We can display everything in-place with fragments, so update
            // the list to highlight the selected item and show the data.
            // We keep highlighted the current selection
            getListView().setItemChecked(index, true);

            // Check what fragment is currently shown, replace if needed.
            Description description = (Description) getFragmentManager()
                    .findFragmentById(R.id.details);
            if (description == null || description.getShownIndex() != index) {
                // Make new fragment to show this selection.
                description = Description.newInstance(index);

                // Execute a transaction, replacing any existing fragment
                // with this one inside the frame.
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.details, description);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            // Otherwise we need to launch a new activity to display
            // the dialog fragment with selected text.
            // That is: if this is a single-pane (e.g., portrait mode on a
            // phone) then fire DetailsActivity to display the details
            // fragment

            // Create an intent for starting the DetailsActivity
            Intent intent = new Intent();

            // explicitly set the activity context and class
            // associated with the intent (context, class)
            intent.setClass(getActivity(), DescriptionActivity.class);

            // pass the current position
            intent.putExtra("index", index);

            startActivity(intent);
        }
    }
}
