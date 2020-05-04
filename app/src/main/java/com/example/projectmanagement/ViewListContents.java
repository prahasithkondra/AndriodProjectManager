package com.example.projectmanagement;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewListContents extends AppCompatActivity {

   /* DatabaseHelper mDatabaseHelper;
    ArrayList<String> listitem;
    ArrayAdapter adapter;
    ListView userlist;*/


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.viewlistcontents );

       // mDatabaseHelper = new DatabaseHelper( this );
       // listitem = new ArrayList<>(  );
       // userlist = findViewById( R.id.simple_list_view );

       // populatelistview();


    }

    /*private void populatelistview() {
        Cursor cursor = mDatabaseHelper.getData();

        if(cursor.getCount() == 0){
            Toast.makeText( this, " Sorry No Data to show", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listitem.add(cursor.getString( 1 ) );
                listitem.add(cursor.getString( 2 ));
                listitem.add( cursor.getString( 7 ) );
                //listitem.add(cursor.getString( 3 ) );
            }
            adapter = new ArrayAdapter<>( this, android.R.layout.simple_list_view, listitem );
            userlist.setAdapter( adapter );

        }



    }*/
}
