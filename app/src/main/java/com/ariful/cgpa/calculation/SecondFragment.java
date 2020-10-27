package com.ariful.cgpa.calculation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ariful.cgpa.DataController;
import com.ariful.cgpa.GradeRepository;
import com.ariful.cgpa.R;
import com.ariful.cgpa.model.Course;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SecondFragment extends Fragment {
    View rootView;
    DataController controller;

    EditText creditEditText, gpaEditText;
    Button addButton;
    TextView cgpaTextView;
    double totalCredit = 0;
    double productOfGpaAndCredit = 0;

    RecyclerView recyclerView;
    CourseRecyclerAdapter adapter;
    List<Course> myCourses = new ArrayList<>();

    FloatingActionButton fab;

    GradeRepository repository;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        rootView =  inflater.inflate(R.layout.fragment_second, container, false);
        controller = DataController.getInstance();

        repository = new GradeRepository(getActivity().getApplication());
        myCourses = repository.GetCourseBySemesterID(controller.getCurrentSemester().getSemesterId());

        creditEditText = rootView.findViewById(R.id.editText_credit_Fragment2nd);
        gpaEditText = rootView.findViewById(R.id.editText_gpa_Fragment2nd);
        addButton = rootView.findViewById(R.id.addButton);
        cgpaTextView = rootView.findViewById(R.id.cgpaTextView);
        recyclerView = rootView.findViewById(R.id.courseRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CourseRecyclerAdapter(myCourses);
        recyclerView.setAdapter(adapter);
        fab = rootView.findViewById(R.id.fab_courseFragment);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setMessage("Do You want to save?")
                        .setTitle("Warning!!")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Save CourseList
                                if(myCourses ==null || myCourses.size() == 0){
                                    Toast.makeText(getActivity(), "Add a Course First", Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    repository.InsertCourseList(myCourses);
                                    Toast.makeText(getActivity(), "Courses Saved", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });

        if(myCourses.size() > 0){
            calculateCGPAList(myCourses);
        }

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                deleteCourse(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(recyclerView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(creditEditText.getText().toString().equals("") || gpaEditText.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Please insert all fields", Toast.LENGTH_SHORT).show();
                } else{
                    calculateCGPA(gpaEditText.getText().toString(), creditEditText.getText().toString());
                }
            }
        });



        Toast.makeText(getActivity(), "Semester: " + controller.getCurrentSemester().getSemesterName(), Toast.LENGTH_SHORT).show();
        return rootView;
    }

    private void calculateCGPAList(List<Course> myCourses) {
        for (int i = 0; i < myCourses.size(); i++) {
            Course temp = myCourses.get(i);
            totalCredit += temp.getCourseCredit();
            productOfGpaAndCredit += (temp.getCourseCredit()*temp.getCourseGPA());
        }

        double cgpa = productOfGpaAndCredit / totalCredit;
        cgpaTextView.setText(String.format("CGPA : %.2f", cgpa));
    }

    private void calculateCGPA(String gpa, String credit){
        double gpaValue = Double.parseDouble(gpa);
        double creditValue = Double.parseDouble(credit);

        productOfGpaAndCredit += (gpaValue * creditValue);
        totalCredit += creditValue;

        double cgpa = productOfGpaAndCredit / totalCredit;
        cgpaTextView.setText(String.format("CGPA : %.2f", cgpa));

        Course course = new Course(gpaValue , creditValue, controller.getCurrentSemester().getSemesterId());
        myCourses.add(course);
        adapter.notifyDataSetChanged();
    }

    private void deleteCourse(int position){
        Course course = myCourses.get(position);
        repository.DeleteCourse(course);
        myCourses.remove(course);
        adapter.notifyDataSetChanged();
    }

}