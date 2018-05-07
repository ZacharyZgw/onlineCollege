package com.zgw.business.impl;

import com.zgw.business.ICourseBusiness;
import com.zgw.core.course.CourseEnum;
import com.zgw.core.course.entity.CourseSection;
import com.zgw.core.course.service.ICourseSectionService;
import com.zgw.vo.CourseSectionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class CourseBusinessImpl implements ICourseBusiness {

    @Autowired
    private ICourseSectionService courseSectionService;
    @Override
    public List<CourseSectionVO> queryCourseSection(Long courseId) {
        List<CourseSectionVO> resultList = new ArrayList<CourseSectionVO>();
        CourseSection queryEntity = new CourseSection();
        queryEntity.setCourseId(courseId);
        queryEntity.setOnsale(CourseEnum.ONSALE.value());//上架

        Map<Long,CourseSectionVO> tmpMap = new LinkedHashMap<Long,CourseSectionVO>();
        Iterator<CourseSection> it = courseSectionService.queryAll(queryEntity).iterator();
        while(it.hasNext()){
            CourseSection item = it.next();
            if(Long.valueOf(0).equals(item.getParentId())){//章
                CourseSectionVO vo = new CourseSectionVO();
                BeanUtils.copyProperties(item, vo);
                tmpMap.put(vo.getId(), vo);
            }else{
                tmpMap.get(item.getParentId()).getSections().add(item);//小节添加到大章中
            }
        }
        for(CourseSectionVO vo : tmpMap.values()){
            resultList.add(vo);
        }
        return resultList;
    }
}
