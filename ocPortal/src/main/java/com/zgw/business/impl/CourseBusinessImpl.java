package com.zgw.business.impl;

import com.zgw.business.ICourseBusiness;
import com.zgw.core.course.CourseEnum;
import com.zgw.core.course.entity.CourseSection;
import com.zgw.core.course.service.ICourseSectionService;
import com.zgw.vo.CourseSectionVo;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class CourseBusinessImpl implements ICourseBusiness {

    @Autowired
    ICourseSectionService courseSectionService;



    @Override
    public List<CourseSectionVo> getCourseSection(Long courseId) {
        List<CourseSectionVo> resultList=
                new ArrayList<>();
        CourseSection queryEntity = new CourseSection();
        queryEntity.setCourseId(courseId);
        queryEntity.setOnsale(CourseEnum.ONSALE.value());
        Map<Long,CourseSectionVo>  tmpMap = new LinkedHashMap<>();
        List<CourseSection> courseSectionList = courseSectionService.queryAll(queryEntity);
        Iterator<CourseSection> iterator = courseSectionList.iterator();
        while (iterator.hasNext()){
            CourseSection item = iterator.next();
            if (Long.valueOf(0).equals(Long.valueOf(item.getParentId()))){//章
                CourseSectionVo courseSectionVo = new CourseSectionVo();
                BeanUtils.copyProperties(item,courseSectionVo);
                tmpMap.put(courseSectionVo.getId(),courseSectionVo);
            }else {//节
                tmpMap.get(item.getParentId()).getSubSectionList().add(item);

            }
        }
        for (CourseSectionVo vo:tmpMap.values()
                ) {
            resultList.add(vo);

        }
        return resultList;


    }

    @Override
    public Long getFirstSectionId(Long courseId) {
        CourseSection qu = new CourseSection();

        return null;
    }
}
