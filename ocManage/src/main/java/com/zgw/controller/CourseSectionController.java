package com.zgw.controller;


import com.zgw.business.ICourseSectionBusiness;
import com.zgw.core.course.entity.CourseSection;
import com.zgw.core.course.service.ICourseSectionService;
import com.zgw.vo.CourseSectionVO;
import com.zgw.web.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/courseSection")
public class CourseSectionController {

    @Autowired
    private ICourseSectionService courseSectionService;

    @Autowired
    private ICourseSectionBusiness courseSectionBusiness;

    @RequestMapping(value = "/getById")
    @ResponseBody
    public String getById(Long id){
        return JsonView.render(this.courseSectionService.getById(id));
    }

    @RequestMapping(value = "/doMerge")
    @ResponseBody
    public String doMerge(CourseSection entity){
        this.courseSectionService.updateSelectivity(entity);
        return new JsonView().toString();
    }
    //交换排序位置
    @RequestMapping(value = "/sortSection")
    @ResponseBody
    public String sortSection(CourseSection entity, Integer sortType){
        CourseSection curCourseSection = this.courseSectionService.getById(entity.getId());
        if(null != curCourseSection){
            CourseSection tmpCourseSection = null;
            if(Integer.valueOf(1).equals(sortType)){//降序
                //比当前sort大的，正序排序的第一个
                tmpCourseSection = this.courseSectionService.getSortSectionMax(curCourseSection);
            }else{
                //比当前sort小的，倒序排序的第一个
                tmpCourseSection = this.courseSectionService.getSortSectionMin(curCourseSection);
            }

            if(null != tmpCourseSection){
                Integer tmpSort = curCourseSection.getSort();
                curCourseSection.setSort(tmpCourseSection.getSort());
                this.courseSectionService.updateSelectivity(curCourseSection);

                tmpCourseSection.setSort(tmpSort);
                this.courseSectionService.updateSelectivity(tmpCourseSection);
            }

        }
        return new JsonView().toString();
    }
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(CourseSection entity){
        this.courseSectionService.delete(entity);
        return new JsonView().toString();
    }

    @RequestMapping(value = "/deleteLogic")
    @ResponseBody
    public String deleteLogic(CourseSection entity){
        this.courseSectionService.deleteLogic(entity);
        return new JsonView().toString();
    }

    //批量添加章节
    @RequestMapping(value = "/batchAdd")
    @ResponseBody
    public String batchAdd(@RequestBody List<CourseSectionVO> batchSections){
        courseSectionBusiness.batchAdd(batchSections);
        return new JsonView().toString();
    }
    /**
     * 导入excel
     */
    @RequestMapping("/doImport")
    @ResponseBody
    public String doImport(Long courseId ,@RequestParam(value="courseSectionExcel",required=true)MultipartFile excelFile){
        try {
            if (null != excelFile && excelFile.getBytes().length > 0) {
                InputStream is = excelFile.getInputStream();
                courseSectionBusiness.batchImport(courseId, is);//批量导入
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JsonView().toString();
    }
}
