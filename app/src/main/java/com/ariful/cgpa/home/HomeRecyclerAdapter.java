package com.ariful.cgpa.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ariful.cgpa.DataController;
import com.ariful.cgpa.R;
import com.ariful.cgpa.model.Semester;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeRecyclerAdapter extends RecyclerView.Adapter<HomeRecyclerAdapter.viewHolder> {

    List<Semester>mySemesterList;

    public HomeRecyclerAdapter(List<Semester> mySemesterList) {
        this.mySemesterList = mySemesterList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.semester_recycle_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Semester currentSemester = mySemesterList.get(position);

        holder.semesterNameTextView.setText("Semester: "  + currentSemester.getSemesterName());
        holder.semesterCreditTextView.setText("Credit: " + Double.toString(currentSemester.getSemesterCredit()));
        //holder.semesterCreditTextView.setText("Credit: " + currentSemester.getSemesterCredit() + "");
    }

    @Override
    public int getItemCount() {
        if (mySemesterList == null || mySemesterList.isEmpty()){
            return 0;
        }
        else
            return mySemesterList.size();
    }

    //Sub class home HomeRecyclerAdapter
    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView semesterNameTextView, semesterCreditTextView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            semesterNameTextView = itemView.findViewById(R.id.semester_item_textview);
            semesterCreditTextView = itemView.findViewById(R.id.semesterCredit_item_textview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            DataController.instance.getHomeFragmentInterface().onSemesterItemClick(mySemesterList.get(getAdapterPosition()));
        }
    }
}
