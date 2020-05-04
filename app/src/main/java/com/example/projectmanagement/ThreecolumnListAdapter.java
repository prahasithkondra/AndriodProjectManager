package com.example.projectmanagement;

import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.text.Transliterator;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class ThreecolumnListAdapter extends ArrayAdapter<Projects> {

    private LayoutInflater inflater;
    private ArrayList<Projects> projects;
    private int resourceid;
    public DatabaseHelper mDatabaseHelper;


    public ThreecolumnListAdapter(@NonNull Context context, int resource, ArrayList<Projects> projects) {
        super( context, resource,projects );
        this.projects = projects;
        inflater = (LayoutInflater) context.getSystemService( context.LAYOUT_INFLATER_SERVICE );
        resourceid = resource;
        mDatabaseHelper = new DatabaseHelper( context );
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(resourceid,null);


        final Projects project = projects.get( position );

        if(projects != null){
            TextView projectname = (TextView) convertView.findViewById( R.id.textView );
            TextView coursename = (TextView) convertView.findViewById( R.id.textView2 );
            TextView duedate = (TextView) convertView.findViewById( R.id.textView3 );
            ImageView ivdelete = (ImageView) convertView.findViewById(R.id.deleteIcon);
            ImageView editIv = (ImageView) convertView.findViewById(R.id.editIcon);

            ivdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    long result = mDatabaseHelper.deleteProjectItem(project.getId());
                    Log.d("dresult",String.valueOf(result));
                    if(result==1)
                    {
                        Toast.makeText(getContext(),"Project Deleted!",Toast.LENGTH_LONG).show();
                        projects.remove(project);
                        notifyDataSetChanged();
                    }
                }
            });





            if (projectname != null){
                projectname.setText( project.getProjectname() );
            }
            if (coursename != null){
                coursename.setText(  project.getCoursename() );
            }
            if (duedate != null){


                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = dateFormat.parse(project.getDuedate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                System.out.println(date);
                duedate.setText(project.getDuedate());

                Date current_date = new Date();
                long diff = date.getDate() - current_date.getDate();

                if(date.after(current_date) | date.compareTo(current_date)==0)
                {
                    if(diff>2 ){
                        convertView.setBackgroundColor(Color.BLUE);
                    }
                    if(diff<=2 && diff>=0)
                    {
                        convertView.setBackgroundColor(Color.RED);
                    }
//                    if(diff>0){
//                        convertView.setBackgroundColor(Color.GREEN);
//                    }
                }
                else if(date.before((current_date)) && diff!=0 )
                {
                    convertView.setBackgroundColor(Color.GREEN);
                }
                else{
                    convertView.setBackgroundColor(Color.RED);
                }
//                if(diff>2 && date.getTime() > current_date.getTime()){
//                    convertView.setBackgroundColor(Color.BLUE);
//                }
//                if(diff<=2 && diff>=0)
//                {
//                    convertView.setBackgroundColor(Color.RED);
//                }
//                if(diff>0 && date.getTime() < current_date.getTime()){
//                    convertView.setBackgroundColor(Color.GREEN);
//                }
            }


        }
        return convertView;
    }

}
