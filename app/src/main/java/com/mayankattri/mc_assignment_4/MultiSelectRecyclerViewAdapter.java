package com.mayankattri.mc_assignment_4;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MultiSelectRecyclerViewAdapter extends SelectableAdapter<MultiSelectRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> mArrayList;
    private Context mContext;
    private ViewHolder.ClickListener clickListener;



    public MultiSelectRecyclerViewAdapter(Context context, ArrayList<String> arrayList, ViewHolder.ClickListener clickListener) {
        this.mArrayList = arrayList;
        this.mContext = context;
        this.clickListener = clickListener;

    }

    // Create new views
    @Override
    public MultiSelectRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_2, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView,clickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

//        viewHolder.tvName.setText(mArrayList.get (position));
        viewHolder.title2.setText(mArrayList.get (position));
        viewHolder.detail2.setText(mArrayList.get (position));
        viewHolder.date2.setText(mArrayList.get (position));
        viewHolder.time2.setText(mArrayList.get (position));

//        viewHolder.selectedOverlay.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener,View.OnLongClickListener  {

        public TextView title2, detail2, date2, time2;
        private ClickListener listener;
//        private final View selectedOverlay;


        public ViewHolder(View itemLayoutView,ClickListener listener) {
            super(itemLayoutView);

            this.listener = listener;

            title2 = (TextView) itemLayoutView.findViewById(R.id.TV_title2);
            detail2 = (TextView) itemLayoutView.findViewById(R.id.TV_detail2);
            date2 = (TextView) itemLayoutView.findViewById(R.id.TV_date2);
            time2 = (TextView) itemLayoutView.findViewById(R.id.TV_time2);
//            selectedOverlay = (View) itemView.findViewById(R.id.selected_overlay);

            itemLayoutView.setOnClickListener(this);

            itemLayoutView.setOnLongClickListener (this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
//                listener.onItemClicked(getAdapterPosition ());
            }
        }
        @Override
        public boolean onLongClick (View view) {
            if (listener != null) {
                return listener.onItemLongClicked(getAdapterPosition ());
            }
            return false;
        }

        public interface ClickListener {
            public void onItemClicked(int position);

            public boolean onItemLongClicked(int position);
        }
        }
    }

