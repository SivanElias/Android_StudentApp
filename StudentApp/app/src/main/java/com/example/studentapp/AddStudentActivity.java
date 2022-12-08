package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studentapp.model.Model;
import com.example.studentapp.model.Student;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        Button cancelBtn = findViewById(R.id.addstudent_cancel_btn);
        Button saveBtn = findViewById(R.id.addstudent_save_btn);
        Button deleteBtn = findViewById(R.id.addstudent_delete_btn);
        EditText nameEt = findViewById(R.id.addstudent_name_et);
        EditText idEt = findViewById(R.id.addstudent_id_et);
        EditText addressEt = findViewById(R.id.addstudent_address_et);
        EditText phoneEt = findViewById(R.id.addstudent_phone_et);
        CheckBox cb = findViewById(R.id.addstudent_cb);

        deleteBtn.setVisibility(View.GONE);

        saveBtn.setOnClickListener(view -> {
            String name = nameEt.getText().toString();
            String id = idEt.getText().toString();
            String address = addressEt.getText().toString();
            String phone = phoneEt.getText().toString();
            Boolean checked = cb.isChecked();

            // save
            Model.instance().addStudent(new Student(name, id, address, phone, checked));
            Intent intent = new Intent(this, StudentRecyclerList.class);
            startActivity(intent);
            finish();
        });

        cancelBtn.setOnClickListener(view -> finish());
    }
}