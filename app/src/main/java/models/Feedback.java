package models;

/**
 * Created by menuka on 3/17/17.
 */

public class Feedback {
    private String rating;
    private String comment;
    private String studentId;
    private String reviewerId;

    public Feedback() {

    }

    public Feedback(String rating, String comment, String studentId, String reviewerId) {
        this.rating = rating;
        this.comment = comment;
        this.studentId = studentId;
        this.reviewerId = reviewerId;
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

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }
}
