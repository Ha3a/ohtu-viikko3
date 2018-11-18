/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Harri
 */
public class Kurssi {

    private String name;
    private String fullName;
    private int[] exercises;
    private String url;
    private int week;
    private String term;
    private int year;
    private ArrayList<Submission> subs = new ArrayList();
    public int totalteht;

    public String getName() {
        return name;
    }
    
    public String getFullName(){
        return fullName;
    }

    public int totalExe() {
        return exercises.length;
    }

    public int weekExe(int viikko) {
        return exercises[viikko];
    }
    
    public void addSub(Submission sub){
        subs.add(sub);
    }
    
    public boolean yesSubs(){
        if(subs.isEmpty()){
            return false;
        }
        return true;
    }
    
    public int montakoteht(){
        int apu = 0;
        for(int i = 0; i < exercises.length; i++){
            apu += exercises[i];
        }
        
        return apu;
    }
    
    
    public String viikottain(){
        int tehtyht = 0;
        int tuntyht = 0;
        int yhtteht = montakoteht();
        
        
        for(Submission sub : subs){
            tehtyht += sub.getTotalExe();
            tuntyht += sub.getHours();
            
            System.out.println("");
            System.out.println("Viikko " + sub.getWeek());
            System.out.println("Tehtyjä tehtäviä " + sub.getTotalExe() + "/" + weekExe(sub.getWeek()) + " aikaa kului " + sub.getHours() + " tehdyt tehtävät: " + Arrays.toString(sub.getExercises()));
        }
        
        System.out.println("Yhteensä " + tehtyht + "/" + yhtteht + " tehtävää " + tuntyht + " tuntia");
        
        return "lul";
    }
    
}
