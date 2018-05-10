package com.zgw.business.impl;

import com.zgw.business.ICourseSectionBusiness;
import com.zgw.core.course.entity.CourseSection;
import com.zgw.core.course.service.ICourseSectionService;
import com.zgw.vo.CourseSectionVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
@Service
public class CourseSectionBusinessImpl implements ICourseSectionBusiness {

    @Autowired
    private ICourseSectionService courseSectionService;
    @Override
    public void batchAdd(List<CourseSectionVO> courseSections) {
        if (CollectionUtils.isNotEmpty(courseSections)){
            //获取最大章节的排序,确定课程章节数量
            Integer maxSortId =
                    this.courseSectionService.getMaxSort(courseSections
                    .get(0).getCourseId());
            for (int i = 0; i <courseSections.size() ; i++) {
                CourseSectionVO tmpVo = courseSections.get(i);
                if (null == maxSortId){
                    maxSortId=0;
                }
                maxSortId +=(i+1);
                CourseSection courseSection = new CourseSection();
                courseSection.setCourseId(tmpVo.getCourseId());
                courseSection.setOnsale(1);
                courseSection.setName(tmpVo.getName());
                courseSection.setParentId(0L);
                courseSection.setSort(maxSortId);
            }

        }

    }

    @Override
    public void batchImport(Long courseId, InputStream is) {

    }
}
