package com.ganeshkumar.favlist;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CategoryitemsActivity extends AppCompatActivity {

    private RecyclerView itemRecyclerView;
    private FloatingActionButton additemsBtn;
    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryitems);

        category = (Category) getIntent().getSerializableExtra(MainActivity.CATEGORY_INTENTKEY);

        Category category = (Category) getIntent().getSerializableExtra(MainActivity.CATEGORY_INTENTKEY);

        setTitle(category.getName());
        itemRecyclerView = findViewById(R.id.items_RV);

        itemRecyclerView.setAdapter(new ItemsRecyclerAdapter(category));
        itemRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        additemsBtn = findViewById(R.id.add_item);

        additemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayItemCreationDialog();

            }
        });

    }

    private void displayItemCreationDialog(){

        final EditText itemEditText = new EditText(this);
        itemEditText.setInputType(InputType.TYPE_CLASS_TEXT);

        new AlertDialog.Builder(this)
                .setTitle("Please Enter The Name Of The Items here... ")
                .setView(itemEditText)
                .setPositiveButton("create Item", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String itemname = itemEditText.getText().toString();
                        category.getItems().add(itemname);

                        ItemsRecyclerAdapter itemsRecyclerAdapter =(ItemsRecyclerAdapter) itemRecyclerView.getAdapter();
                        itemsRecyclerAdapter.notifyItemInserted(category.getItems().size() - 1);
                        dialog.dismiss();

                    }
                })
                .create().show();

    }

    @Override
    public void onBackPressed() {

        Bundle bundle = new Bundle();
        bundle.putSerializable(MainActivity.CATEGORY_INTENTKEY, category);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
    }
}