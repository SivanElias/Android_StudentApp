package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentapp.model.Model;
import com.example.studentapp.model.Student;

public class StudentDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        TextView nameTv = findViewById(R.id.studentdetails_name_tv);
        TextView idTv = findViewById(R.id.studentdetails_id_tv);
        TextView addressTv = findViewById(R.id.studentdetails_address_tv);
        TextView phoneTv = findViewById(R.id.studentdetails_phone_tv);
        CheckBox cb = findViewById(R.id.studentdetails_cb);
        Button editBtn = findViewById(R.id.studentdetails_edit_btn);

        if (getIntent().getExtras() != null) {
            int studentPos = (int) getIntent().getSerializableExtra("studentPos");
            Student s = Model.instance().getStudentByPos(studentPos);

            nameTv.setText(s.getName());
            idTv.setText(s.getId());
            addressTv.setText(s.getAddress());
            phoneTv.setText(s.getPhone());
            cb.setChecked(s.getChecked());

            Intent intentEdit = new Intent(this, EditStudentActivity.class);
            editBtn.setOnClickListener(v -> {
                intentEdit.putExtra("studentPos", studentPos);
                startActivity(intentEdit);
                finish();
            });

        }
    }
}