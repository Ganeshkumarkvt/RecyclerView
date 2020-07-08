package com.ganeshkumar.favlist;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView itemTextView;

    public ItemViewHolder(View item){

        super(item);

        itemTextView = item.findViewById(R.id.item_txtview);

    }

}
