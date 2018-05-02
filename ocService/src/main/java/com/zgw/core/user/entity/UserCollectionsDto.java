package com.zgw.core.user.entity;

public class UserCollectionsDto extends UserCollections {
    private String courseName;
    private String coursePicture;
    private String courseBrief;

    public String getCourseBrief() {
        return courseBrief;
    }

    public void setCourseBrief(String courseBrief) {
        this.courseBrief = courseBrief;
    }

    public String getCoursePicture() {
        return coursePicture;
    }

    public void setCoursePicture(String coursePicture) {
        this.coursePicture = coursePicture;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "UserCollectionsDto{" +
                "courseName='" + courseName + '\'' +
                ", coursePicture='" + coursePicture + '\'' +
                '}';
    }
}
