package com.ariful.cgpa.calculation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ariful.cgpa.R;
import com.ariful.cgpa.model.Course;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.viewHolder> {

    List<Course>allCourses;

    public CourseRecyclerAdapter(List<Course> allCourses) {
        this.allCourses = allCourses;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_item_layout,parent,false);

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Course course = allCourses.get(position);

        holder.courseGPATextView.setText(course.getCourseGPA() + "");
        holder.courseCreditTextView.setText(course.getCourseCredit() + "");
        holder.courseNameTextView.setText("Course" + (position+1));
    }

    @Override
    public int getItemCount() {
        if(allCourses == null || allCourses.size() == 0)
            return 0;
        else
            return allCourses.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView courseNameTextView, courseCreditTextView, courseGPATextView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            courseNameTextView = itemView.findViewById(R.id.CourseNameTextViewId);
            courseCreditTextView = itemView.findViewById(R.id.CourseCreditTextViewId);
            courseGPATextView = itemView.findViewById(R.id.CourseGPATextViewId);
        }
    }
}
