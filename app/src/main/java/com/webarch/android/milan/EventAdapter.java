package com.webarch.android.milan;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webarch.android.milan.HelperObjects.Event;

import java.util.ArrayList;

/**
 * Created by ajitesh on 13/2/16.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private ArrayList<Event> mDataset;
    OnItemClickListener mItemClickListener;
    int colors[];

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public CardView cardView;
        public TextView mTextView;
        public TextView DescView;
        public ViewHolder(View v) {
            super(v);
            cardView=(CardView)v.findViewById(R.id.card_view);
            mTextView = (TextView) v.findViewById(R.id.info_text);
            DescView=(TextView) v.findViewById(R.id.desc_text);
            v.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }

    }
    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    // Provide a suitable constructor (depends on the kind of dataset)
    public EventAdapter(ArrayList<Event> myDataset,Context context) {
        mDataset = myDataset;
        colors=context.getResources().getIntArray(R.array.custom_card_colors);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_card_view, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.cardView.setCardBackgroundColor(colors[position % 5]);
        holder.mTextView.setText( mDataset.get(position).getName());
        if(mDataset.get(position).getIntro()!=null) {
            if (mDataset.get(position).getIntro().length() > 100) {
                holder.DescView.setText(mDataset.get(position).getIntro().substring(0, 100) + " . . .");
            } else {
                holder.DescView.setText(mDataset.get(position).getIntro());
            }
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
