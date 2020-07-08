package com.ganeshkumar.favlist;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    private TextView txtnumber;
    private TextView txtname;

    public  CategoryViewHolder(View view){

        super(view);

        txtnumber = view.findViewById(R.id.category_number);
        txtname = view.findViewById(R.id.category_name);

    }

    public TextView getTxtnumber() {
        return txtnumber;
    }

    public TextView getTxtname() {
        return txtname;
    }
}
