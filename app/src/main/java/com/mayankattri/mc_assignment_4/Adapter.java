package com.mayankattri.mc_assignment_4;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayank on 31/10/16.
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Note> notesList;

    public Adapter(List<Note> notesList) {
        this.notesList = notesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, detail, date, time;
        public CheckBox cb;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.TV_title);
            detail = (TextView) view.findViewById(R.id.TV_detail);
            date = (TextView) view.findViewById(R.id.TV_date);
            time = (TextView) view.findViewById(R.id.TV_time);
            cb = (CheckBox) itemView.findViewById(R.id.CB);

            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        System.out.println("checked");
                    }
                }
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Note note = notesList.get(position);

        holder.title.setText(note.getTitle());
        holder.detail.setText(note.getDetail());
        boolean status;
        status = note.getStatus() != 0;
        holder.cb.setChecked(status);

        String date_time[] = note.getDate().split(" ");
        holder.date.setText(date_time[0]);
        holder.time.setText(date_time[1]);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
