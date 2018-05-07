package com.zgw.business.impl;

import com.zgw.business.IPortalBusiness;
import com.zgw.core.consts.entity.Classify;
import com.zgw.core.consts.service.IClassifyService;
import com.zgw.core.course.CourseEnum;
import com.zgw.core.course.entity.Course;
import com.zgw.core.course.entity.CourseQueryDto;
import com.zgw.core.course.entity.CourseSection;
import com.zgw.core.course.service.ICourseSectionService;
import com.zgw.core.course.service.ICourseService;
import com.zgw.vo.ConstsClassifyVO;
import com.zgw.vo.CourseSectionVO;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class PortalBusinessImpl implements IPortalBusiness {

    @Autowired
    private IClassifyService classifyService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private ICourseSectionService courseSectionService;
    @Override
    public List<ConstsClassifyVO> queryAllClassify() {
        List<ConstsClassifyVO> resultList = new ArrayList<ConstsClassifyVO>();
        for(ConstsClassifyVO vo : this.queryAllClassifyMap().values()){
            resultList.add(vo);
        }
        return resultList;
    }

    @Override
    public Map<String, ConstsClassifyVO> queryAllClassifyMap() {
        Map<String,ConstsClassifyVO> resultMap = new LinkedHashMap<String,ConstsClassifyVO>();
        Iterator<Classify> it =classifyService.queryAll().iterator();
        while(it.hasNext()){
            Classify c = it.next();
            if("0".equals(c.getParentCode())){//一级分类
                ConstsClassifyVO vo = new ConstsClassifyVO();
                BeanUtils.copyProperties(c, vo);
                resultMap.put(vo.getCode(), vo);
            }else{//二级分类
                if(null != resultMap.get(c.getParentCode())){
                    resultMap.get(c.getParentCode()).getSubClassifyList().add(c);//添加到子分类中
                }
            }
        }
        return resultMap;
    }

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

    @Override
    public void prepareRecomdCourses(List<ConstsClassifyVO> classifyVoList) {
        if(CollectionUtils.isNotEmpty(classifyVoList)){
            for(ConstsClassifyVO item : classifyVoList){
                CourseQueryDto queryEntity = new CourseQueryDto();
                queryEntity.setCount(5);
                queryEntity.descSortField("weight");
                queryEntity.setClassify(item.getCode());//分类code

                List<Course> tmpList = this.courseService.queryList(queryEntity);
                if(CollectionUtils.isNotEmpty(tmpList)){
                    item.setRecomdCourseList(tmpList);
                }
            }
        }
    }
}
