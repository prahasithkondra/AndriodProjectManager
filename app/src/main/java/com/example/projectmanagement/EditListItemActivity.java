package com.example.projectmanagement;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;

public class EditListItemActivity extends AppCompatActivity {

    public static Button datePick_btn;
    public static String date;
    int ListItemId;
    private ArrayList<Projects> projects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editlistitem);

        EditText editTextprojectname = (EditText) findViewById(R.id.editTextprojectname);
        EditText editTextcoursename = (EditText) findViewById(R.id.editTextcoursename);
        datePick_btn = (Button) findViewById(R.id.datePick_btn);
        Button btnsave = (Button) findViewById(R.id.btnsave);

        //Intent intent = getIntent();
        //ListItemId = intent.getIntExtra("ListItemId", -1);


        btnsave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String project_name = editTextprojectname.getText().toString();
               // String course_name =  editTextcoursename.getText().toString();

                //int due_date = finalValuedd;
                //String due_date = date;


                    Toast.makeText( EditListItemActivity.this, "Data Updated Successfully", Toast.LENGTH_LONG ).show();


            }

        } );

    }

    public void showPicker(View v)
    {
    DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
}




    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user

            date = Integer.toString(year) + "-" +Integer.toString(month+1) +"-"+  Integer.toString(day);
            datePick_btn.setText(date);
        }
    }


}


