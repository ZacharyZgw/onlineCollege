package com.zgw.core.user.entity;

public class UserCollectionsDto extends UserCollections {
    private String courseName;
    private String coursePicture;

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
