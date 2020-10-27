package com.ariful.cgpa;

import com.ariful.cgpa.home.HomeFragment;
import com.ariful.cgpa.home.HomeFragmentInterface;
import com.ariful.cgpa.model.Semester;

public class DataController {
    public static DataController instance;
    public static DataController getInstance(){
        if (instance == null){
            instance = new DataController();
        }
        return instance;
    }

    HomeFragmentInterface homeFragmentInterface;
    Semester currentSemester;


    //getter setter function
    public HomeFragmentInterface getHomeFragmentInterface() {
        return homeFragmentInterface;
    }

    public void setHomeFragmentInterface(HomeFragmentInterface homeFragmentInterface) {
        this.homeFragmentInterface = homeFragmentInterface;
    }

    public Semester getCurrentSemester() {
        return currentSemester;
    }

    public void setCurrentSemester(Semester currentSemester) {
        this.currentSemester = currentSemester;
    }
}
