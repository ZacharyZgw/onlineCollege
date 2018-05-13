package com.zgw.controller;

import com.zgw.business.IPortalBusiness;
import com.zgw.core.auth.domain.AuthUser;
import com.zgw.core.auth.service.IAuthUserService;
import com.zgw.core.consts.entity.Classify;
import com.zgw.core.consts.service.IClassifyService;
import com.zgw.core.course.entity.Course;
import com.zgw.core.course.service.ICourseService;
import com.zgw.core.statics.entity.CourseStudyStaticsDto;
import com.zgw.core.statics.entity.StaticsVO;
import com.zgw.core.statics.service.ICourseStudyStaticsService;
import com.zgw.page.TailPage;
import com.zgw.util.CalendarUtil;
import com.zgw.util.JsonUtil;
import com.zgw.vo.ConstsClassifyVO;
import com.zgw.vo.CourseSectionVO;
import com.zgw.web.JsonView;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IPortalBusiness portalBusiness;

    @Autowired
    private ICourseStudyStaticsService staticsService;

    @Autowired
    private IAuthUserService authUserService;

    @Autowired
    private IClassifyService classifyService;

    @RequestMapping("/pagelist")
    public String list(Course queryEntity, TailPage<Course> page, Model model){
        if(StringUtils.isNotEmpty(queryEntity.getName())){
            queryEntity.setName(queryEntity.getName().trim());
        }else{
            queryEntity.setName(null);
        }
        page.setPageSize(5);
        page = courseService.queryPage(queryEntity,page);
        model.addAttribute("page",page);
        model.addAttribute("queryEntity",queryEntity);
        model.addAttribute("curNav","course");
        return "cms/course/pagelist";
    }
    /**
     * 课程上下架
     */
    @RequestMapping("/doSale")
    @ResponseBody
    public String doSale(Course entity){
        Long courseId = entity.getCourseId();
        courseService.updateSelectivity(entity);
        //更新课程总时长

        return new JsonView().toString();
    }
    /**
     * 课程删除
     */
    @RequestMapping("/doDelete")
    @ResponseBody
    public String doDelete(Course entity){
        courseService.deleteLogic(entity);
        return new JsonView().toString();
    }

    /**
     * 根据id获取
     */
    @RequestMapping("/getById")
    @ResponseBody
    public String getById(Long id){
        return JsonView.render(courseService.getById(id));
    }

    /**
     * 课程详情
     */
    @RequestMapping("/read")
    public String courseRead(Long id,Model model){
        Course course = courseService.getByCourseId(id);
        if(null == course){
            return "error/404";
        }
        //ModelAndView mv = new ModelAndView("cms/course/read");
        model.addAttribute("curNav", "course");
        model.addAttribute("course", course);

        //课程章节
        List<CourseSectionVO> chaptSections = this.portalBusiness.queryCourseSection(id);
        model.addAttribute("chaptSections", chaptSections);

        //课程分类
        Map<String,ConstsClassifyVO> classifyMap = portalBusiness.queryAllClassifyMap();
        //所有一级分类
        List<ConstsClassifyVO> classifysList = new ArrayList<ConstsClassifyVO>();
        for(ConstsClassifyVO vo : classifyMap.values()){
            classifysList.add(vo);
        }
        model.addAttribute("classifys", classifysList);

        List<Classify> subClassifys = new ArrayList<Classify>();
        for(ConstsClassifyVO vo : classifyMap.values()){
            subClassifys.addAll(vo.getSubClassifyList());
        }
        model.addAttribute("subClassifys", subClassifys);//所有二级分类

        //获取报表统计信息
        CourseStudyStaticsDto staticsDto = new CourseStudyStaticsDto();
        staticsDto.setCourseId(course.getId());
        staticsDto.setEndDate(new Date());
        staticsDto.setStartDate(CalendarUtil.getPreNDay(new Date(), 7));

        StaticsVO staticsVo = staticsService.queryCourseStudyStatistics(staticsDto);
        if(null != staticsVo){
            try {
                model.addAttribute("staticsVo", JsonUtil.toJson(staticsVo));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "cms/course/read";
    }
    @RequestMapping("/add")
    public String add(Model model){
        //获取课程分类Po
        Map<String,ConstsClassifyVO> classifyVOMap = portalBusiness.queryAllClassifyMap();
        List<ConstsClassifyVO> classifyVOList = new ArrayList<>();
        for (ConstsClassifyVO vo:classifyVOMap.values()
             ) {
            classifyVOList.add(vo);
            
        }
        model.addAttribute("curNav","course");
        model.addAttribute("classifys",classifyVOList);
        //获取二级分类
        List<Classify> subClassifys = new ArrayList<>();
        for (ConstsClassifyVO vo:classifyVOMap.values()
             ) {
            subClassifys.addAll(vo.getSubClassifyList());

        }
        model.addAttribute("subClassifys",subClassifys);
        return "cms/course/add";
    }
    @RequestMapping("/doMerge")
    @ResponseBody
    public String doMerge(Course entity, @RequestParam MultipartFile pictureImg ){
        /*
        * TODO:改为七牛云存储图片
        * */
        if (!pictureImg.isEmpty()){
            String imgPath = "res/i/course/"+pictureImg.getOriginalFilename();
            logger.error("imgPath",imgPath);
            entity.setPicture(imgPath);
        }
        /*
        * 判断courseId是否存在
        * */
        if (null != entity){
            Course course = this.courseService.getByCourseId(entity.getCourseId());
            if (null != course){
                //课程id已经存在
                return JsonView.render(2,"课程id已经存在").toString();
            }
        }
        /*
        * 判断教师是否存在
        * */
        if (StringUtils.isNotEmpty(entity.getUsername())){
            AuthUser teacher = authUserService.getByUsername(entity.getUsername());
            if (null == teacher){
                return JsonView.render(1,"讲师不存在").toString();
            }
        }else {
            return JsonView.render(1,"讲师名称不能为空").toString();
        }
        if (null != entity.getId()){
            //执行更新操作
            this.courseService.updateSelectivity(entity);
        }else {
            //执行添加课程操作
            if (StringUtils.isNotEmpty(entity.getClassify())){
                Classify classify = this.classifyService.getByCode(entity.getClassify());
                if (null != classify){
                    entity.setClassifyName(classify.getName());
                }
            }
            if (StringUtils.isNotEmpty(entity.getSubClassify())){
                Classify subClassify = this.classifyService.getByCode(entity.getSubClassify());
                if (null != subClassify){
                    entity.setSubClassifyName(subClassify.getName());
                }
            }
            this.courseService.createSelectivity(entity);
        }
        return JsonView.render(entity).toString();
    }
    //继续添加章节
    @RequestMapping("/append")
    public String  appendSection(Long courseId,Model model){
        Course course = courseService.getByCourseId(courseId);
        if(null == course)
            return "error/404";

        //ModelAndView mv = new ModelAndView("cms/course/append");
        model.addAttribute("curNav", "course");
        model.addAttribute("course", course);

        List<CourseSectionVO> chaptSections = this.portalBusiness.queryCourseSection(courseId);
        model.addAttribute("chaptSections", chaptSections);

        return "cms/course/append";
    }

}
