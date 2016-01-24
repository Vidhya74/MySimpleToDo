package com.codepath.mysimpletodo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.secact.EditItem;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import com.secact.EditItem;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> todoItems;
    ArrayAdapter<String> atoDoAdapter;
    ListView lvItems;
    EditText etEditText;
    final int REQUEST_CODE=20;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();

        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(atoDoAdapter);
        etEditText = (EditText) findViewById(R.id.etEditText);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                todoItems.remove(position);
                atoDoAdapter.notifyDataSetChanged();
                writeItems();
                return true;


            }


        });


        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                           @Override
                                           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                              String retval = todoItems.get(position);

                                              // Object obj =atoDoAdapter.getItem(position);
                                               //String value =obj.toString();
                                               Intent i =new Intent(MainActivity.this, EditItem.class);
                                               i.putExtra("value",retval);
                                               i.putExtra("position", position);
                                               startActivityForResult(i ,REQUEST_CODE);


                                           }
                                       }

        );


    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode ,Intent data){

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
         System.out.println("I am here");
         String changedtext =data.getExtras().getString("changedtodo");
           int itemno = data.getIntExtra("changedpos" ,0);


          todoItems.set(itemno,changedtext);
            atoDoAdapter.notifyDataSetChanged();
            writeItems();

        }



    }

    public void populateArrayItems() {

        //lvItems = (ListView)findViewById(R.id.lvItems);
        readItems();
        atoDoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);



        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //  setSupportActionBar(toolbar);

        // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //  @Override
        //public void onClick(View view) {
        //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        // .setAction("Action", null).show();
    }

    //  }

    private void readItems(){
        File filesDir =getFilesDir();
        File file=new File(filesDir,"todo.txt");
        try{
            todoItems = new ArrayList<String>(FileUtils.readLines(file));
        }
        catch(IOException e)
        {
            todoItems =new ArrayList<String>();
        }

    }
    private void writeItems(){
        File filesDir =getFilesDir();
        File file=new File(filesDir,"todo.txt");
        try{
            FileUtils.writeLines(file, todoItems);
        }
        catch(IOException e)
        {e.printStackTrace();}

    }
    public void onAddItem(View V) {

        String itemText = etEditText.getText().toString();
        atoDoAdapter.add(itemText);
        etEditText.setText("");
        writeItems();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
