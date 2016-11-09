package com.mayankattri.mc_assignment_4;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements MultiSelectRecyclerViewAdapter.ViewHolder.ClickListener {

    private String MESSAGE = this.getClass().getSimpleName();
    static List<Note> notesList = new ArrayList<>();
    private RecyclerView recyclerView;
    public static Adapter mAdapter;
    public LinearLayout v;

    private MultiSelectRecyclerViewAdapter mAdapter2;
    private RecyclerView.LayoutManager mLayoutManager;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        DBHandler db = new DBHandler(getActivity());

        // Inserting Notes
//        Log.d("Insert: ", "Inserting ..");
//        db.addNote(new Note("Title1", "Detail1"));
//        db.addNote(new Note("Title2", "Detail2"));
//        db.addNote(new Note("Title3", "Detail3"));
//        db.addNote(new Note("Title4", "Detail4"));
//        db.addNote(new Note("Title5", "Detail5"));

        // Reading all notes
        Log.d("Reading: ", "Reading all notes");
        List<Note> notes = db.getAllNotes();

        notesList = notes;
        v = (LinearLayout) rootView.findViewById(R.id.view);

        for (Note n : notes) {
            String log = "ID.: " + n.getID() + "\nTitle: " + n.getTitle() + "\nDetail: "
                    + n.getDetail() + "\nStatus: " + n.getStatus();
            Log.d("Notes : ", log);
        }

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mAdapter = new Adapter(notes);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("notePosition", position);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
//                v.setBackgroundColor(Color.parseColor("#000000"));
            }
        }));

//        recyclerView.setHasFixedSize(true);
//        mAdapter = new MultiSelectRecyclerViewAdapter(getActivity(), notesList, );
//        recyclerView.setAdapter (mAdapter);

        return rootView;
    }

    @Override
    public void onItemClicked(int position) {

    }

    @Override
    public boolean onItemLongClicked(int position) {
        return false;
    }

    private void toggleSelection(int position) {
        mAdapter2.toggleSelection (position);
    }
}
