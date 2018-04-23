package com.zgw.controller;


import com.zgw.core.auth.service.IAuthUserService;
import com.zgw.core.course.entity.CourseComment;
import com.zgw.core.course.entity.CourseSection;
import com.zgw.core.course.service.ICourseCommentService;
import com.zgw.core.course.service.ICourseSectionService;
import com.zgw.page.TailPage;
import com.zgw.vo.CourseCommentVo;
import com.zgw.web.JsonView;
import com.zgw.web.SessionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/courseComment")
public class CourseCommentController {

    @Autowired
    private ICourseCommentService courseCommentService;

    @Autowired
    private ICourseSectionService courseSectionService;

    @Autowired
    private IAuthUserService authUserService;


    /**
    * @author ZacharyZhang
    * @date 2018/4/22 21:46
    * 加载courseInfo.html的评论问答信息
     * courseId
     * CourseSectionId
     * type
    */
    @RequestMapping("/segment")
    public String segment(CourseComment entity, TailPage<CourseComment> page, Model model){

        if (null == entity.getCourseId() || null ==entity.getType()){
            return "error/404";
        }
        TailPage<CourseComment> commentTailPage= courseCommentService.queryPage(entity,page);
        model.addAttribute("page",commentTailPage);
        return "courseSegment";
    }

    /**
    * @author ZacharyZhang
    * @date 2018/4/22 21:54
    * 发表评论
    *
    */
    @RequestMapping("/doComment")
    @ResponseBody
    public String doComment(HttpServletRequest request,String identiryCOde,CourseComment entity){
        if (null != identiryCOde && identiryCOde.equalsIgnoreCase(SessionContext.getIdentifyCode(request))){
            return JsonView.render(2).toString();//验证码错误
        }
        if (entity.getContent().trim().length()>200){
            return JsonView.render(3).toString();//文字太长
        }
        if (entity.getContent().trim().length() ==0){
            return JsonView.render(4).toString();//文字为空
        }
        //携带参数的时候要把别人评论的id加上
        if (null != entity.getRefId()){//对别人的评论或问答进行回复
            CourseComment refComment = this.courseCommentService.getById(entity.getRefId());//得到别人的评论
            if (null != refComment ){
                //得到当前评论的课程章节id
                CourseSection curCourseSection = this.courseSectionService.getById(refComment.getSectionId());
                if(null != curCourseSection){
                    entity.setRefContent(refComment.getContent());
                    entity.setRefId(refComment.getId());
                    //以下部分无论是ref还是用户的评论,内容都是一样的
                    entity.setCourseId(refComment.getCourseId());
                    entity.setSectionId(refComment.getSectionId());
                    entity.setCourseName(refComment.getCourseName());
                    entity.setSectionTitle(refComment.getSectionTitle());
                    entity.setType(refComment.getType());
                    //设置当前用户的其他属性
                    entity.setUsername(SessionContext.getUsername());
                    entity.setToUsername(refComment.getUsername());
                    //entity.setContent(entity.getContent());
                    entity.setCreateUser(SessionContext.getUsername());
                    entity.setUpdateUser(SessionContext.getUsername());
                    this.courseCommentService.createSelectivity(entity);
                    CourseCommentVo courseCommentVo = new CourseCommentVo();
                    BeanUtils.copyProperties(entity,courseCommentVo);
                    courseCommentVo.setHeader(this.authUserService.getById(SessionContext.getUserId()).getHeader());
                    //把entity的内容返回到页面展示
                    return new JsonView(0,"对别人的评论/问答成功",courseCommentVo).toString();
                }
            }

        }else {
            //用户自己创建的评论和问答
            CourseSection curCourseSection = this.courseSectionService.getById(entity.getSectionId());
            if (null != curCourseSection){
                entity.setSectionTitle(curCourseSection.getName());
                entity.setCreateUser(SessionContext.getUsername());
                entity.setUpdateUser(SessionContext.getUsername());
                entity.setToUsername(entity.getCreateUser());
                entity.setUsername(SessionContext.getUsername());
                this.courseCommentService.createSelectivity(entity);
                CourseCommentVo courseCommentVo = new CourseCommentVo();
                BeanUtils.copyProperties(entity,courseCommentVo);
                courseCommentVo.setHeader(this.authUserService.getById(SessionContext.getUserId()).getHeader());
                return new JsonView(1,"发表评论成功",courseCommentVo).toString();
            }
        }
        return new JsonView(5,"评论失败").toString();
    }

}
