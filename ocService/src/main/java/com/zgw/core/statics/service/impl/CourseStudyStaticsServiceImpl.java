package com.zgw.core.statics.service.impl;

import com.zgw.core.statics.dao.ICourseStudyStaticVODao;
import com.zgw.core.statics.entity.CourseStudyStaticsDto;
import com.zgw.core.statics.entity.StaticsVO;
import com.zgw.core.statics.service.ICourseStudyStaticsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseStudyStaticsServiceImpl implements ICourseStudyStaticsService {

    @Autowired
    private ICourseStudyStaticVODao entityDao;
    @Override
    public StaticsVO queryCourseStudyStatistics(CourseStudyStaticsDto queryEntity) {
        List<CourseStudyStaticsDto> list = entityDao.queryCourseStudyStatistics(queryEntity);

        StaticsVO returnVo = new StaticsVO();
        List<String> categories = new ArrayList<String>();
        List<Integer> data = new ArrayList<Integer>();

        if(CollectionUtils.isNotEmpty(list)){
            for(CourseStudyStaticsDto item : list){
                categories.add(item.getDateStr());
                data.add(item.getTotalCount());
            }
            returnVo.setCategories(categories);
            returnVo.setData(data);
        }

        return returnVo;
    }
}
