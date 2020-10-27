package com.ariful.cgpa;


import android.app.Application;
import android.os.AsyncTask;

import com.ariful.cgpa.model.Course;
import com.ariful.cgpa.model.Semester;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class GradeRepository {
    private CourseDao courseDao;
    private SemesterDao semesterDao;

    List<Semester> mySemesterList = new ArrayList<>();
    List<Course> allCourses = new ArrayList<>();

    public GradeRepository(Application application) {
        GradeDatabase db = GradeDatabase.getDatabase(application);
        courseDao = db.courseDao();
        semesterDao = db.semesterDao();
    }

    public void InsertSemester(Semester semester){
        new InsertTask(semesterDao).execute(semester);
    }

    public void InsertCourseList(List<Course> myCourses){
        new courseListTaskt(courseDao).execute(myCourses);
    }
    public List<Course> GetCourseBySemesterID(int semesterId){
        try {
            allCourses = new GetCourseListTask(courseDao).execute(semesterId).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allCourses;
    }



    public List<Semester> getAllSemester(){
        try {
            mySemesterList = new GetAllSemesterTask(semesterDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mySemesterList;
    }

    public void DeleteCourse(Course course){
        new DeleteCourseTask(courseDao).execute(course);
    }



//Backgroud task
    private static class InsertTask extends AsyncTask<Semester,Void,Void>{
        //AsyncTask used for doing work in background
        private SemesterDao dao;

        public InsertTask(SemesterDao semesterDao) {
            dao = semesterDao;
        }

        @Override
        protected Void doInBackground(Semester... semesters) {
            dao.InsertSemester(semesters[0]);
            return null;
        }
    }

    private static class GetAllSemesterTask extends AsyncTask<Void,Void,List<Semester>>{
        SemesterDao dao;

        public GetAllSemesterTask(SemesterDao dao) {
            this.dao = dao;
        }

        @Override
        protected List<Semester> doInBackground(Void... voids) {
            return dao.GetAllSemesterFromSemesterDao();
        }
    }

    private static class courseListTaskt extends AsyncTask<List<Course>,Void,Void>{
        CourseDao dao;

        public courseListTaskt(CourseDao courseDao) {
            this.dao = courseDao;
        }

        @Override
        protected Void doInBackground(List<Course>... lists) {
            dao.InsertAllCourseList(lists[0]);
            return null;
        }
    }

    private static class GetCourseListTask extends AsyncTask<Integer,Void,List<Course>>{

        CourseDao dao;

        public GetCourseListTask(CourseDao courseDao) {
            this.dao = courseDao;
        }

        @Override
        protected List<Course> doInBackground(Integer... integers) {
            return dao.GetCoursesBySemesterId(integers[0]);
        }
    }

    private static class DeleteCourseTask extends AsyncTask<Course , Void, Void>{
        CourseDao dao;

        public DeleteCourseTask(CourseDao courseDao) {
            this.dao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            dao.DeleteCourse(courses[0]);
            return null;
        }
    }
}
