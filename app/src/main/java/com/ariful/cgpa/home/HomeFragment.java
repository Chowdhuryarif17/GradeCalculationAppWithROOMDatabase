package com.ariful.cgpa.home;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ariful.cgpa.DataController;
import com.ariful.cgpa.GradeRepository;
import com.ariful.cgpa.R;
import com.ariful.cgpa.model.Course;
import com.ariful.cgpa.model.Semester;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HomeFragment extends Fragment implements HomeFragmentInterface {
    View root;
    GradeRepository repository;
    RecyclerView recyclerView;
    HomeRecyclerAdapter adapter;
    List<Semester> allSemesters = new ArrayList<>();
    DataController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root =  inflater.inflate(R.layout.fragment_home, container, false);
        repository = new GradeRepository(getActivity().getApplication());
        recyclerView = root.findViewById(R.id.home_RecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        
        allSemesters = repository.getAllSemester();
        adapter = new HomeRecyclerAdapter(allSemesters);
        recyclerView.setAdapter(adapter);

        controller = DataController.getInstance();
        controller.setHomeFragmentInterface(this);

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_user_input_dialog);

                final EditText semesterNameEditText = dialog.findViewById(R.id.dialog_semesterName_editText);
                Button createButton = dialog.findViewById(R.id.dialog_create_button);
                createButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(semesterNameEditText.getText().toString().equals("")){
                            Toast.makeText(getActivity(), "Please Insert Semester Name", Toast.LENGTH_LONG).show();
                        }
                        else {
                            String semesterName = semesterNameEditText.getText().toString();
                            Toast.makeText(getActivity(), semesterName + " Is Semester Name", Toast.LENGTH_SHORT).show();
                            insertSemester(semesterName);

                            dialog.dismiss();
                        }
                    }
                });

                dialog.show();
            }
        });

        return root;
    }
    

    private void insertSemester(String semesterName){

        Semester tempSemester = new Semester(semesterName, 0.0);

        allSemesters.add(tempSemester);
        adapter.notifyDataSetChanged();
        repository.InsertSemester(tempSemester);
    }

    @Override
    public void onSemesterItemClick(Semester semester) {
        controller.setCurrentSemester(semester);
        NavHostFragment.findNavController(HomeFragment.this)
                .navigate(R.id.action_FirstFragment_to_SecondFragment);
    }

//    private void deleteSemester(int position){
//        Semester semester = .get(position);
//        repository.DeleteCourse(course);
//        myCourses.remove(course);
//        adapter.notifyDataSetChanged();
//    }
}