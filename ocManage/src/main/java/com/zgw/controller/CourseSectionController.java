package com.zgw.controller;


import com.zgw.business.ICourseSectionBusiness;
import com.zgw.core.course.service.ICourseSectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/courseSection")
public class CourseSectionController {

    @Autowired
    private ICourseSectionService courseSectionService;

    @Autowired
    private ICourseSectionBusiness courseSectionBusiness;
}
