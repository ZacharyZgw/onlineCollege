package com.zgw.core.statics.entity;

import java.util.Date;

public class CourseStudyStaticsDto {
    private Integer totalCount;

    private String dateStr;

    private Date startDate;

    private Date endDate;

    private Long courseId;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CourseStudyStaticsDto{" +
                "totalCount=" + totalCount +
                ", dateStr='" + dateStr + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", courseId=" + courseId +
                '}';
    }
}
