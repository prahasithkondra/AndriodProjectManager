package com.example.projectmanagement;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class createproject extends AppCompatActivity{
    private static final String TAG = "createproject";
    public static String date;
    DatabaseHelper mDatabaseHelper;
    private Button btnsave, btnview;
    public static Button datePick_btn;
    private EditText editTextprojectname,editTextcoursename,editTextcoursenumber,editTextinstructorname,editTextprojectnumber,editTextprojectdesc,editTextduedate;
    MediaPlayer mysong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.createproject );
        mysong = MediaPlayer.create(getApplicationContext(),R.raw.song1);
        mysong.start();
        editTextprojectname = (EditText) findViewById( R.id.editTextprojectname );
        editTextcoursename = (EditText) findViewById( R.id.editTextcoursename );
        editTextcoursenumber = (EditText) findViewById( R.id.editTextcoursenumber );
        datePick_btn = (Button) findViewById(R.id.datePick_btn);
        String tempcoursenumber = editTextcoursenumber.getText().toString();
        int valueCN = 0;
        if (!"".equals( tempcoursenumber )){
            valueCN = Integer.parseInt( tempcoursenumber );
        }
        editTextinstructorname = (EditText) findViewById( R.id.editTextinstructorname );
        editTextprojectnumber = (EditText) findViewById( R.id.editTextprojectnumber );
        String tempprojectnumber = editTextprojectnumber.getText().toString();
        int valuePN = 0;
        if(!"".equals( tempprojectnumber )){
            valuePN = Integer.parseInt( tempprojectnumber );
        }
        editTextprojectdesc = (EditText) findViewById( R.id.editTextprojectdesc );
//        editTextduedate = (EditText) findViewById( R.id.editTextduedate );
        /*String tempduedate = editTextduedate.getText().toString();
        int valuedd = 0;
        if(!"".equals( tempduedate )){
            valuedd = Integer.parseInt( tempduedate );
        }*/
        btnsave = (Button) findViewById( R.id.btnsave );
        btnview = (Button) findViewById( R.id.btnview );
        mDatabaseHelper = new DatabaseHelper( this );

        final int finalValueCN = valueCN;
        final int finalValuePN = valuePN;
        //final int finalValuedd = valuedd;
        btnsave.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String project_name = editTextprojectname.getText().toString();
                String course_name =  editTextcoursename.getText().toString();
                int course_number = finalValueCN;
                String Instructor_name = editTextinstructorname.getText().toString();
                int project_number = finalValuePN;
                String project_Desc = editTextprojectdesc.getText().toString();
                //int due_date = finalValuedd;
                //String due_date = date;

                if(project_name.length() != 0 && course_name.length()!= 0 && date!=null ){
                    Toast.makeText( createproject.this, "Data inserted sucessfully", Toast.LENGTH_SHORT ).show();
                    mDatabaseHelper.addData(project_name,course_name,course_number,Instructor_name,project_number,project_Desc,date );
                }else{
                    Toast.makeText( createproject.this, "Data Not Inserted Please check for Essential fields like (Project name,course name and due date)", Toast.LENGTH_LONG ).show();
                }


                }

        } );

        btnview.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getBaseContext(), MainActivity.class );
                startActivity( intent );


            }
        } );




    }


    @Override
    protected void onPause() {
        super.onPause();
        mysong.release();
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




