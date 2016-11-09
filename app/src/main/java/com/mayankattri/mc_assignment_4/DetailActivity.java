package com.mayankattri.mc_assignment_4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.Adapter;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    static PagerViewAdapter mPagerViewAdapter;
    static ViewPager mViewPager;
    static int notePosition;
    DBHandler db = new DBHandler(this);
    List<Note> notes = MainActivityFragment.notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            notePosition = extras.getInt("notePosition");
        }

        mPagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mPagerViewAdapter);
        mViewPager.setCurrentItem(notePosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_edit) {
            edit();
            return true;
        }
        else if (id == R.id.action_delete) {
            confirmation();
            return true;
        }
        else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    public void edit() {
        int np = mViewPager.getCurrentItem();
        Note task = notes.get(np);
        View activeView= mViewPager.getFocusedChild();
        EditText d = (EditText) activeView.findViewById(R.id.TV_tv);
        String detailText = d.getText().toString();
        task.setDetail(detailText);
        System.out.println(np);
        System.out.println(detailText);
        System.out.println(task.getTitle()+" "+task.getDetail());
        db.updateNote(task);
    }

    public void confirmation() {
        ConfirmationFragment D = new ConfirmationFragment();
        D.show(getFragmentManager(), "ConfirmationFragment");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
            // This activity is NOT part of this app's task, so create a new task
            // when navigating up, with a synthesized back stack.
            TaskStackBuilder.create(this)
                    // Add all of this activity's parents to the back stack
                    .addNextIntentWithParentStack(upIntent)
                    // Navigate up to the closest parent
                    .startActivities();
        } else {
            // This activity is part of this app's task, so simply
            // navigate up to the logical parent activity.
            NavUtils.navigateUpTo(this, upIntent);
        }
    }

    public static class DemoObjectFragment extends Fragment {

        public static final String ARG_OBJECT = "object";
        public static final String ARG_STATUS = "status";
        public static EditText detail;
        DBHandler db;
        List<Note> notes = MainActivityFragment.notesList;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.pager_view_item, container, false);

            Bundle args = getArguments();
            db = new DBHandler(getActivity());

            detail = (EditText) rootView.findViewById(R.id.TV_tv);
            detail.setText(args.getString(ARG_OBJECT, "Empty"));

            final ToggleButton B_done = (ToggleButton) rootView.findViewById(R.id.B_done);
            int status = args.getInt(ARG_STATUS, 0);
            if (status == 0) {
                B_done.setChecked(false);
            } else {
                B_done.setChecked(true);
            }

            B_done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        update(1);
                    } else {
                        update(0);
                    }
                }
            });

            return rootView;
        }

        public void update(int s) {
            int np = mViewPager.getCurrentItem();
            Note task = notes.get(np);
            task.setStatus(s);
            db.updateNote(task);
        }
    }

}
