package com.example.projectmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private static final String TAG = "MainActivity";

    DatabaseHelper mDatabaseHelper;
    //SQLiteDatabase db;
    //private ListView mlistView;
   ArrayList<Projects> listitems;
    ArrayAdapter adapter;
    ListView listView;
    Projects projects;

    MediaPlayer mysong;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        mysong = MediaPlayer.create(getApplicationContext(),R.raw.song1);
        mysong.start();
       // mlistView = (ListView) findViewById( R.id.simple_list_view );
       mDatabaseHelper = new DatabaseHelper( this );
        listitems = new ArrayList<>(  );
        //userlist = findViewById( R.id.simple_list_view );
        //populatelistview();

        Cursor data = mDatabaseHelper.getData();
        int numrows = data.getCount();
        if(numrows == 0){
            Toast.makeText( this, "Sorry! No data in DataBase to show", Toast.LENGTH_SHORT ).show();
        }else {
            Toast.makeText( this, "Projects in RED are due Soon", Toast.LENGTH_LONG ).show();
            while (data.moveToNext()){
                projects = new Projects(data.getInt(0),data.getString( 1 ),data.getString( 2 ),data.getString( 7 )) ;
                listitems.add( projects );


            }
            ThreecolumnListAdapter adapter = new ThreecolumnListAdapter(this,R.layout.viewlistcontents,listitems);
            listView = (ListView) findViewById( R.id.simple_list_view );
            listView.setAdapter( adapter );

        }



        toolbar = findViewById( R.id.main_toolbar );
        setSupportActionBar( toolbar );
        drawerLayout = findViewById( R.id.drawer_layout );
        navigationView = findViewById( R.id.nav_view );

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle( this,
                drawerLayout,
                toolbar,
                R.string.opennavdrawer,
                R.string.closenavdrawer
        );
        drawerLayout.addDrawerListener( actionBarDrawerToggle );
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener( this );


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent2 = new Intent(MainActivity.this, EditListItemActivity.class);
                intent2.putExtra("ListItemId", position);
                startActivity(intent2);

            }
        });

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
            //ThreecolumnListAdapter adapter = new ThreecolumnListAdapter(this,R.layout.viewlistcontents,listitem);
            adapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, listitem );
            userlist.setAdapter( adapter );

        }



    }*/




    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen( GravityCompat.START )) {
            drawerLayout.closeDrawer( GravityCompat.START );

        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mysong.release();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        switch (id){

            case R.id.nav_about:
                Toast.makeText( this, "Email ID: bbatt027@uottawa.ca & pkond057@uottawa.ca", Toast.LENGTH_SHORT ).show();
                //Intent intent = new Intent(getBaseContext(),about.class);
                //startActivity(intent);
                break;
            case R.id.nav_newproject:
                Intent intent1 = new Intent(this, createproject.class );
                startActivity( intent1 );
                break;



        }
        drawerLayout.closeDrawer( GravityCompat.START );

        return true;
    }
}
