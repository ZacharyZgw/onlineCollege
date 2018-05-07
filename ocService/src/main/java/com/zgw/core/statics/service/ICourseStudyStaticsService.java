package com.zgw.core.statics.service;

import com.zgw.core.statics.entity.CourseStudyStaticsDto;
import com.zgw.core.statics.entity.StaticsVO;

public interface ICourseStudyStaticsService {
    /**
     *统计课程学习情况
     **/
    public StaticsVO queryCourseStudyStatistics(CourseStudyStaticsDto queryEntity);
}
