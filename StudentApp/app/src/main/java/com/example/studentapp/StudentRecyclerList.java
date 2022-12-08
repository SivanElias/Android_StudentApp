package com.example.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.studentapp.model.Model;
import com.example.studentapp.model.Student;

import java.util.List;

public class StudentRecyclerList extends AppCompatActivity {
    List<Student> data;
    Button addBtn;
    StudentRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recycler_list);

        data = Model.instance().getAllStudents();

        /*RecyclerView include:
         * 1. Layout Manager
         * 2. Adapter
         * 3. ViewHolder
         * 4. Row View*/
        RecyclerView list = findViewById(R.id.studentrecycler_list);
        list.setHasFixedSize(true);

        // 1
        list.setLayoutManager(new LinearLayoutManager(this));

        // 2
        adapter = new StudentRecyclerAdapter();
        list.setAdapter(adapter);

        // create intent for student details
        Intent intentDetails = new Intent(this, StudentDetailsActivity.class);

        /*-> Handle row click in activity*/
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                intentDetails.putExtra("studentPos", pos);
                startActivity(intentDetails);
            }
        });

        addBtn = findViewById(R.id.studentrecycler_btn);

        addBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddStudentActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        data = Model.instance().getAllStudents();

        // reload data
        adapter.notifyDataSetChanged();
    }

    // 3
    class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView nameTv;
        TextView idTv;
        CheckBox cb;

        public StudentViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.studentlistrow_name_tv);
            idTv = itemView.findViewById(R.id.studentlistrow_id_tv);
            cb = itemView.findViewById(R.id.studentlistrow_cb);
            cb.setOnClickListener(v -> {
                int pos = (int) cb.getTag();
                Student st = data.get(pos);
                st.setChecked(cb.isChecked());
            });
            itemView.setOnClickListener(view -> {
                int pos = getAdapterPosition();
                Log.d("TAG", "row click " + pos);
                listener.onItemClick(pos);
            });
        }

        public void bind(Student st, int pos) {
            nameTv.setText(st.getName());
            idTv.setText(st.getId());
            cb.setChecked(st.getChecked());
            cb.setTag(pos);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int pos);
    }

    class StudentRecyclerAdapter extends RecyclerView.Adapter<StudentViewHolder> {
        /*Set listener to catch view row click and handle in the activity ->*/
        OnItemClickListener listener;

        void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // 4
            View view = getLayoutInflater().inflate(R.layout.student_list_row, parent, false);
            return new StudentViewHolder(view, listener);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
            Student st = data.get(position);
            holder.bind(st, position);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}