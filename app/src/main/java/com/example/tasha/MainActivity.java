package com.example.tasha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        //CRUD Operations

        //Inserting contacts
        Log.e("Insert: ", "Inserting...");
        db.addContact(new contacts("Tasha", "478593938347"));
        db.addContact(new contacts("Teabag", "292938474748"));
        db.addContact(new contacts("Wacere", "582947448384"));
        db.addContact(new contacts("harsh", "1846548454"));

        //Reading all contacts
        Log.e("Reading: ", "Reading all contacts...");
        List<contacts> contacts = db.getAllContacts();

        for(contacts cn : contacts){
            String log = "Id: "+cn.get_id()+" Name: " + cn.get_name() + " ,Phone: "+cn.get_phone_number();
            Log.e("Name: ", log);
        }

        Log.e("Insert: ", "Inserting...");
        db.addCourses(new courses("Tasha", "478593938347"));
        db.addCourses(new courses("Teabag", "292938474748"));
        db.addCourses(new courses("Wacere", "582947448384"));
        db.addCourses(new courses("harsh", "1846548454"));

        //Reading all contacts
        Log.e("Reading: ", "Reading all contacts...");
        List<courses> courses = db.getAllCourses();

        for(courses cn : courses){
            String log = "IDY: "+cn.get_idy()+" Coursename: " + cn.get_course_name() + " ,Lecturer: "+cn.get_lecturer();
            Log.e("Coursename: ", log);
        }
    }

}
