package com.ganeshkumar.favlist;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class CategoryManager {

    private Context mContext;

    public CategoryManager(Context context){
        mContext = context;
    }

    public void  saveCategory(Category category){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        HashSet itemsHashset = new HashSet(category.getItems());

        editor.putStringSet(category.getName(), itemsHashset);

        editor.apply();

    }

    public ArrayList<Category> retrievCategories(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        Map<String, ?> data = sharedPreferences.getAll();

        ArrayList<Category> categories = new ArrayList<>();

        for (Map.Entry<String, ?> entry : data.entrySet()){

            Category category = new Category(entry.getKey(), new ArrayList<String>((HashSet)entry.getValue()));

            categories.add(category);

        }
        return categories;

    }

}
