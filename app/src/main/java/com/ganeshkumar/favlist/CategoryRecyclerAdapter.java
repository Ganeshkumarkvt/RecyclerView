package com.ganeshkumar.favlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    interface CategoryIsClicked{

        void categoryIsClicked(Category category);
    }


    //String [] categories = {"Hobbies", "Sports", "Games", "Gadgets", "Countries"};

    private ArrayList<Category> categories;
    private CategoryIsClicked mCategoryIsClicked;

    public CategoryRecyclerAdapter(ArrayList<Category> categories, CategoryIsClicked mCategoryIsClicked){
        this.categories = categories;
        this.mCategoryIsClicked = mCategoryIsClicked;
    }


    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.category_viewholder, parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
      holder.getTxtnumber().setText(Integer.toString(position + 1));
      holder.getTxtname().setText(categories.get(position).getName());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              mCategoryIsClicked.categoryIsClicked(categories.get(position));
          }
      });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void  addCategory(Category category){

        categories.add(category);

        notifyItemInserted(categories.size() - 1);

    }
}

