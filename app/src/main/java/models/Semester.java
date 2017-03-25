package models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by menuka on 2/21/17.
 */


@IgnoreExtraProperties
public class Semester {
    private String number;
    private String year;
    private String studentId;
    private String sgpa;
    private boolean enabled;

    private final List<Module> modules =  new ArrayList<>();

    public void addModule(Module module){
        this.modules.add(module);
    }

    public void editModule(Module module){

    }

    @Override
    public String toString() {
        return "Semester{" +
                "number='" + number + '\'' +
                ", year='" + year + '\'' +
                ", studentId='" + studentId + '\'' +
                ", sgpa='" + sgpa + '\'' +
                ", enabled=" + enabled +
                ", modules=" + modules +
                '}';
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSgpa() {
        return sgpa;
    }

    public void setSgpa(String sgpa) {
        this.sgpa = sgpa;
    }

    public Module getModuleAtIndex(int index){
        return modules.get(index);
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Semester() {}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
