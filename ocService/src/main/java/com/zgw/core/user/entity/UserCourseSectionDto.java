package com.zgw.core.user.entity;

public class UserCourseSectionDto extends UserCourseSection{
    private String username;
    private String courseName;
    private String SectionName;
    private String userHeader;
    private String coursePicture;


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getCourseName() {
            return courseName;
        }

        public void setCourseName(String courseName) {
            this.courseName = courseName;
        }

        public String getSectionName() {
            return SectionName;
        }

        public void setSectionName(String sectionName) {
            SectionName = sectionName;
        }

        public String getUserHeader() {
            return userHeader;
        }

        public void setUserHeader(String userHeader) {
            this.userHeader = userHeader;
        }

        public String getCoursePicture() {
            return coursePicture;
        }

        public void setCoursePicture(String coursePicture) {
            this.coursePicture = coursePicture;
        }

    @Override
    public String toString() {
        return "UserCourseSectionDto{" +
                "username='" + username + '\'' +
                ", courseName='" + courseName + '\'' +
                ", SectionName='" + SectionName + '\'' +
                ", userHeader='" + userHeader + '\'' +
                ", coursePicture='" + coursePicture + '\'' +
                '}';
    }
}
