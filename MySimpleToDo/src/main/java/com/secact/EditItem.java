package com.secact;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.codepath.mysimpletodo.MainActivity;
import com.codepath.mysimpletodo.R;

public class EditItem extends AppCompatActivity {

  EditText etEditItem;

    String textval;
    int textpos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        textval =getIntent().getStringExtra("value");
        textpos =getIntent().getIntExtra("position", 0);
       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



        etEditItem = (EditText) findViewById(R.id.etEditItem);
        //etEditItem.setText(textval.toCharArray() ,Integer.valueOf(textpos) ,textval.length());
        etEditItem.setText(textval);
        etEditItem.setSelection(textval.length());
        etEditItem.requestFocus();

    }

    public void onSave(View V) {


        String itemText = etEditItem.getText().toString();
        Intent data=new Intent();
        data.putExtra("changedtodo",itemText);

        data.putExtra("changedpos", textpos);

        setResult(RESULT_OK, data);
        finish();
    }





}
