package models;

/**
 * Created by menuka on 2/21/17.
 */

public class Student {
    private String UID;
    private String indexNo;
    private String firstName;
    private String lastName;
    private String department;

    public Student(){}

    public Student(String indexNo, String firstName, String lastName) {
        this.indexNo = indexNo;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getIndexNo() {
        return indexNo;
    }

    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
}
