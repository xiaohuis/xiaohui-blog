package com.xiaohuis.controller;

import com.xiaohuis.entity.Tag;
import com.xiaohuis.service.BlogService;
import com.xiaohuis.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  前台标签查询前端控制器
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
@Controller

public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    /**
     * 标签查询页面，查询所有标签，以及对应标签的博客
     * @param tagId
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/tags")
        public String findTags( @RequestParam(name = "tagId", required = false) Long tagId,
                           @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                                   int pageNum,
                           Model model){
        List<Tag> tagList = tagService.findAllTags();
        if(tagId == null || String.valueOf(tagId) == ""){
            tagId = tagList.get(0).getId();
        }
        model.addAttribute("tagList",tagList);
        model.addAttribute("tagId",tagId);
        model.addAttribute("blogs", blogService.findBlogCombinationByTagIdAndPaging(tagId,pageNum));
        return "blog/tags";
    }

}

