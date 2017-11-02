package com.example.tasha;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 10/31/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //All static variables
    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "contactsManager";

    //Contacts table name
    private static final String TABLE_CONTACTS="contacts";

    //Contacts Table Column Names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    private static final String TABLE_COURSE="courses";
    private static final String KEY_IDY = "id";
    private static final String KEY_COURSE_NAME = "coursename";
    private static final String KEY_LECTURER = "teacher";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, " +KEY_PH_NO + " TEXT"
                + ");";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_COURSE_TABLE = "CREATE TABLE " + TABLE_COURSE + "("
                + KEY_IDY + " INTEGER PRIMARY KEY, " + KEY_COURSE_NAME + " TEXT, " +KEY_LECTURER + " TEXT"
                + ");";
        db.execSQL(CREATE_COURSE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop older table if it existed

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);

        //Create table again
        onCreate(db);
    }

    public void addContact(contacts contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, contact.get_name()); //Contact Name
        values.put(KEY_PH_NO, contact.get_phone_number()); //Phone number

        //inserting database values
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); //closing the database connection
    }

    public void addCourses(courses course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_COURSE_NAME, course.get_course_name()); //Contact Name
        values.put(KEY_LECTURER, course.get_lecturer()); //Phone number

        //inserting database values
        db.insert(TABLE_COURSE, null, values);
        db.close(); //closing the database connection
    }

    //Getting a single contact
    public contacts getContact(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{ KEY_ID, KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{ String.valueOf(id)}, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        contacts contact = new contacts(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        //return contact
        return contact;
    }

    public courses getCourse(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COURSE, new String[]{ KEY_IDY, KEY_COURSE_NAME, KEY_LECTURER}, KEY_IDY + "=?",
                new String[]{ String.valueOf(id)}, null, null, null, null);

        if(cursor != null)
            cursor.moveToFirst();

        courses course = new courses(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        //return contact
        return course;
    }

    //Getting all contacts
    public List<contacts> getAllContacts(){
        List<contacts> ContactList = new ArrayList<contacts>();

        //Selecting all query
        String select_query = "SELECT * FROM " + TABLE_CONTACTS +";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        //looping through all rows to add to the list
        if(cursor.moveToFirst()){
            do{
                contacts contact = new contacts();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_name(cursor.getString(1));
                contact.set_phone_number(cursor.getString(2));

                //Adding a contact to the list
                ContactList.add(contact);

            } while(cursor.moveToNext());
        }
        return ContactList;
    }

    public List<courses> getAllCourses(){
        List<courses> CoursesList = new ArrayList<>();

        //Selecting all query
        String select_query = "SELECT * FROM " + TABLE_COURSE +";";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        //looping through all rows to add to the list
        if(cursor.moveToFirst()){
            do{
                courses course = new courses();
                course.set_idy(Integer.parseInt(cursor.getString(0)));
                course.set_course_name(cursor.getString(1));
                course.set_lecturer(cursor.getString(2));

                //Adding a contact to the list
                CoursesList.add(course);

            } while(cursor.moveToNext());
        }
        return CoursesList;
    }

    //Getting contacts count
    public int getContactsCount(){
        String CountQuery = "SELECT * FROM " + TABLE_CONTACTS + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(CountQuery, null);
        cursor.close();

        //Return count
        return cursor.getCount();
    }

    public int getCoursesCount(){
        String CountQuery = "SELECT * FROM " + TABLE_COURSE + ";";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(CountQuery, null);
        cursor.close();

        //Return count
        return cursor.getCount();
    }

    //updating a single contact
    public int updateContact(contacts contact){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME, contact.get_name()); //Contact Name
        values.put(KEY_PH_NO, contact.get_phone_number()); //Phone number

        //Updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + "=?",
                new String[]{ String.valueOf(contact.get_id()) });

    }

    public int updateCourse(courses course){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_COURSE_NAME, course.get_course_name()); //Contact Name
        values.put(KEY_LECTURER, course.get_lecturer()); //Phone number

        //Updating row
        return db.update(TABLE_COURSE, values, KEY_IDY + "=?",
                new String[]{ String.valueOf(course.get_idy()) });

    }

    //Deleting a single contact
    public void deleteContact(contacts contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + "=?",
                new String[]{ String.valueOf(contact.get_id()) });

        db.close();
    }

    public void deleteCourse(courses course){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COURSE, KEY_IDY + "=?",
                new String[]{ String.valueOf(course.get_idy()) });

        db.close();
    }

}
