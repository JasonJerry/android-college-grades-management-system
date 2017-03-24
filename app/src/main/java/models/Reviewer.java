package models;

/**
 * Created by menuka on 3/17/17.
 */

public class Reviewer {
    private String userId;
    private String firstName;
    private String lastName;
    private String birthday;
    private String employeeNo;

    public Reviewer() {

    }

    public Reviewer(String userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }


}
