package com.example.studentapp.model;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/*Singelton model*/
public class Model {
    private static final Model _instance = new Model();
    List<Student> data = new LinkedList<>();

    public static Model instance() {
        return _instance;
    }

    /*No one can make an new instance of this class from the outside*/
    private Model() {
        for (int i = 0; i < 5; i++) {
            addStudent(new Student("name " + i, "" + i, "", "", false));
        }
    }

    public List<Student> getAllStudents() {
        return data;
    }

    public void addStudent(Student st) {
        data.add(st);
    }

    public Student getStudentByPos(int pos) {
        return data.get(pos);
    }

    public void editStudent(Student newS, int pos) {
        data.set(pos, newS);
    }

    public void deleteStudent(int pos) {
        data.remove(pos);
    }
}
