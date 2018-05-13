package com.zgw.controller;

import com.zgw.core.course.entity.CourseComment;
import com.zgw.core.course.service.ICourseCommentService;
import com.zgw.page.TailPage;
import com.zgw.storage.QiniuStorage;
import com.zgw.web.JsonView;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/courseComment")
public class CourseCommentController {
    @Autowired
    private ICourseCommentService courseCommentService;

    /**
     * 课程评论管理
     */
    @RequestMapping("/pagelist")
    public ModelAndView commentSegment(CourseComment queryEntity , TailPage<CourseComment> page){
        ModelAndView mv = new ModelAndView("cms/course/readComment");
        queryEntity.setCourseId(1L);
        TailPage<CourseComment> commentPage = this.courseCommentService.queryPage(queryEntity, page);
        //处理用户头像
        /*for(CourseComment item : commentPage.getItems()){
            if(StringUtils.isNotEmpty(item.getHeader())){
                item.setHeader(QiniuStorage.getUrl(item.getHeader()));
            }
        }*/
        mv.addObject("page", commentPage);
        mv.addObject("queryEntity", queryEntity);
        return mv;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(CourseComment entity){
        courseCommentService.delete(entity);
        return new JsonView().toString();
    }


}
