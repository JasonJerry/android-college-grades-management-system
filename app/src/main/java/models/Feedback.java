package models;

/**
 * Created by menuka on 3/17/17.
 */

public class Feedback {
    private String id;
    private String rating;
    private String comment;
    private String studentId;
    private String reviewer;
    private String date;

    public Feedback() {

    }

    public Feedback(String rating, String comment, String studentId, String reviewerId, String date) {
        this.rating = rating;
        this.comment = comment;
        this.studentId = studentId;
        this.reviewer = reviewerId;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
