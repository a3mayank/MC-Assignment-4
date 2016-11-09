package com.mayankattri.mc_assignment_4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by mayank on 31/10/16.
 */
public class PagerViewAdapter extends FragmentStatePagerAdapter {

    List<Note> notes = MainActivityFragment.notesList;

    public PagerViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new DetailActivity.DemoObjectFragment();
        Bundle args = new Bundle();
        args.putString(DetailActivity.DemoObjectFragment.ARG_OBJECT, notes.get(i).getDetail());
        args.putInt(DetailActivity.DemoObjectFragment.ARG_STATUS, notes.get(i).getStatus());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return notes.get(position).getTitle();
    }
}
