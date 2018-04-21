package com.zgw.business.impl;

import com.zgw.business.IPortalBusiness;
import com.zgw.core.consts.entity.Classify;
import com.zgw.core.consts.service.IClassifyService;
import com.zgw.core.course.entity.Course;
import com.zgw.core.course.entity.CourseQueryDto;
import com.zgw.core.course.service.ICourseService;
import com.zgw.vo.ClassifyVo;
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
    /**
     * 获取所有，包括一级分类&二级分类
     */

    public List<ClassifyVo> queryAllClassify() {
        List<ClassifyVo> classifyVoList = new ArrayList<>();
        for (ClassifyVo classifyVo:this.queryAllClassifyMap().values()
             ) {
            classifyVoList.add(classifyVo);
        }
        return classifyVoList;
    }


    public Map<String, ClassifyVo> queryAllClassifyMap() {
        Map<String,ClassifyVo> resultMap = new LinkedHashMap<>();
        Iterator<Classify> it = classifyService.queryAll().iterator();
        while (it.hasNext()){
            Classify classify = it.next();
            //一级分类
            if ("0".equals(classify.getParentCode())){
                ClassifyVo classifyVo = new ClassifyVo();
                //将classify的属性值赋值给classifyVo
                BeanUtils.copyProperties(classify,classifyVo);
                resultMap.put(classifyVo.getCode(),classifyVo);
            }else{
                //二级分类
                if (null != classify.getParentCode()){
                    resultMap.get(classify.getParentCode()).getSubClassifyList().add(classify);
                }
            }
        }
        return resultMap;
    }

    @Override
    public void prepareRecomdCourses(List<ClassifyVo> classifyVoList) {
        if(CollectionUtils.isNotEmpty(classifyVoList)){
            for(ClassifyVo item : classifyVoList){
                CourseQueryDto queryEntity = new CourseQueryDto();
                queryEntity.setCount(4);
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
