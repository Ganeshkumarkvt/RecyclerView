package com.ganeshkumar.favlist;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoryRecyclerAdapter.CategoryIsClicked {

    private RecyclerView categoryRecyclerView;
    private CategoryManager mCategoryManager = new CategoryManager(this);
    public static final String CATEGORY_INTENTKEY = "CATEGORY_KEY";
    public static final int MAIN_ACTIVITY_REQUEST_CODE = 10345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Category> categories = mCategoryManager.retrievCategories();

        categoryRecyclerView = findViewById(R.id.category_RV);

        categoryRecyclerView.setAdapter(new CategoryRecyclerAdapter(categories,this));
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayCreateCategoryDialog();
            }
        });



    }

    private void displayCreateCategoryDialog(){
        String alerttitle = "Enter the name of the category";
        String positivebtn = "create";

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        final EditText categoryedit = new EditText(this);
        categoryedit.setInputType(InputType.TYPE_CLASS_TEXT);

        alertBuilder.setTitle(alerttitle);
        alertBuilder.setView(categoryedit);

        alertBuilder.setPositiveButton(positivebtn, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Category category = new Category(categoryedit.getText().toString(), new ArrayList<String>());
                mCategoryManager.saveCategory(category);

                CategoryRecyclerAdapter categoryRecyclerAdapter = (CategoryRecyclerAdapter) categoryRecyclerView.getAdapter();
                categoryRecyclerAdapter.addCategory(category);

                dialog.dismiss();
                displayCategoryItems(category);

            }
        });

        alertBuilder.create().show();

    }

    private void displayCategoryItems(Category category){

        Intent categoryItemsIntent = new Intent(this, CategoryitemsActivity.class);
        categoryItemsIntent.putExtra(CATEGORY_INTENTKEY, category);
        startActivityForResult(categoryItemsIntent, MAIN_ACTIVITY_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MAIN_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK){

            if(data != null){
                mCategoryManager.saveCategory((Category) data.getSerializableExtra(CATEGORY_INTENTKEY));
                updateCategories();

            }

        }

    }

    private void updateCategories() {

        ArrayList<Category> categories = mCategoryManager.retrievCategories();
        categoryRecyclerView.setAdapter(new CategoryRecyclerAdapter(categories, this));

    }

    @Override
    public void categoryIsClicked(Category category) {
        displayCategoryItems(category);
    }
}
