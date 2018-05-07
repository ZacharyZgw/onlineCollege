package com.zgw.core.statics.dao;

import com.zgw.core.statics.entity.CourseStudyStaticsDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseStudyStaticVODao {
    /**
     *统计课程学习情况
     **/
    public List<CourseStudyStaticsDto> queryCourseStudyStatistics(CourseStudyStaticsDto queryEntity);
}
