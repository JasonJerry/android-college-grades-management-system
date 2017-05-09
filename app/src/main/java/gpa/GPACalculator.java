package gpa;

import java.util.ArrayList;
import java.util.HashMap;

import models.Module;
import models.Semester;

/**
 * Created by menuka on 4/27/17.
 */

public class GPACalculator {
    private static HashMap<String, String> grades = new HashMap<>();
    private static GPACalculator instance;

    private static void initGrades(){
        grades.put("A+", "4.2");
        grades.put("A", "4.0");
        grades.put("A-", "3.7");
        grades.put("B+", "3.4");
        grades.put("B", "3.0");
        grades.put("B-", "2.7");
        grades.put("C+", "2.4");
        grades.put("C", "2.0");
        grades.put("C-", "1.5");
        grades.put("D", "1.0");
        grades.put("F", "0.0");
        grades.put("I-we", "0.0");
        grades.put("I-ce", "0.0");
    }

    private GPACalculator(){
        initGrades();
    }

    public static GPACalculator getInstance(){
        if(instance == null){
            instance = new GPACalculator();
        }

        return instance;
    }

    public String getSGPA(ArrayList<Module> modules){
        double totalCredits = getTotalCredits(modules);
        double totalGrades = 0;
        for (Module m: modules){
            double product = Double.parseDouble(grades.get(m.getGrade())) * Double.parseDouble(m.getCredits());
            totalGrades += product;
        }

        double gpa = totalGrades/totalCredits;

        System.out.println("Returning GPA: " + gpa);
        return Double.toString(gpa);
    }

    public void getOGPA(ArrayList<Semester> semesters){

    }

    public double getTotalCredits(ArrayList<Module> modules){
        double totalCredits = 0;
        for(Module m: modules){
            totalCredits += Double.parseDouble(m.getCredits());
        }

        System.out.println("Returning TotalCredits: " + totalCredits);
        return totalCredits;
    }
}
