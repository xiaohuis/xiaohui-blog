package com.xiaohuis.controller;


import com.xiaohuis.entity.Blog;
import com.xiaohuis.entity.Classification;
import com.xiaohuis.service.BlogService;
import com.xiaohuis.service.ClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  前台分类查询前端控制器
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
@Controller
public class ClassificationController {

    @Autowired
    private ClassificationService classificationService;

    @Autowired
    private BlogService blogService;

    /**
     * 分类查询页面，查询所有分类，以及对应分类的博客
     * @param blog
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/classifications")
    public String findClassifications(Blog blog,
                           @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                           int pageNum,
                           Model model){
        List<Classification> classificationList = classificationService.findAllClassification();
        if(blog.getClassificationId() == null || String.valueOf(blog.getClassificationId()) == ""){
            blog.setClassificationId(classificationList.get(0).getId());
        }
        model.addAttribute("classificationList",classificationList);
        model.addAttribute("classificationId",blog.getClassificationId());
        model.addAttribute("blogs", blogService.findBlogCombinationByTypeIdAndPaging(blog,pageNum));
        return "blog/classifications";
    }

}

