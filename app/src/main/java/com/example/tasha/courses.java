package com.example.tasha;

/**
 * Created by Francis on 10/31/2017.
 */

public class courses {
    //private variables

    int _id;
    String _course_name;
    String _lecturer;

    public courses(){}

    public courses(int id, String _course_name, String _lecturer){
        this._id  =id;
        this._course_name = _course_name;
        this._lecturer= _lecturer;

    }
    public courses(String _course_name, String _lecturer){
        this._course_name = _course_name;
        this._lecturer= _lecturer;
    }

    //Getting ID
    public int get_idy(){
        return this._id;
    }

    //Setting ID
    public void set_idy(int _id) {
        this._id = _id;
    }

    //Get Phone Number
    public String get_course_name() {
        return _course_name;
    }

    //Set Phone Number
    public void set_course_name(String _course_name) {
        this._course_name = _course_name;
    }

    //Get name
    public String get_lecturer() {
        return _lecturer;
    }

    //Set Name
    public void set_lecturer(String _lecturer) {
        this._lecturer = _lecturer;
    }

}
