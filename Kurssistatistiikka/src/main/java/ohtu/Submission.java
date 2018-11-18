package ohtu;

import java.util.Arrays;

public class Submission {
    private int week;
    private String course;
    private int hours;
    private int[] exercises;

    public void setWeek(int week) {
        this.week = week;
    }
    
    public void setCourse(String course){
        this.course = course;
    }

    public void setHours(int hours){
        this.hours = hours;
    }
    
    public void setExercises(int[] exercises){
        this.exercises = exercises;
    }
    
    public int getWeek() {
        return week;
    }
    
    public String getCourse(){
        return course;
    }
    
    public int getHours(){
        return hours;
    }
    
    public int[] getExercises(){
        return exercises;
    }
    
    public int getTotalExe(){
        return exercises.length;
    }
    
    


    @Override
    public String toString() {
        return course + ", viikko " + week + " tehtyjä tehtäviä yhteensä " + exercises.length + " aikaa kului " + hours + " tehdyt tehtävät: " + Arrays.toString(exercises);
    }
    
}