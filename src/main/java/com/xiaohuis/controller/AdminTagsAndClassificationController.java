package com.xiaohuis.controller;

import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.LogActions;
import com.xiaohuis.constant.Message;
import com.xiaohuis.entity.Classification;
import com.xiaohuis.service.ClassificationService;
import com.xiaohuis.service.LogsService;
import com.xiaohuis.service.TagService;
import com.xiaohuis.util.FrontEndCommonsUtil;
import com.xiaohuis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  管理员标签和分类管理前端控制器
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
@Controller
@RequestMapping("/admin/tags-and-classification")
public class AdminTagsAndClassificationController {

    @Autowired
    private FrontEndCommonsUtil frontEndCommonsUtil;

    @Autowired
    private ClassificationService classificationService;

    @Autowired
    private TagService tagService;

    @Autowired
    private LogsService logsService;

    /**
     * 查询所有标签和分类，前往标签和分类列表页面
     * @param model
     * @return
     */
    @GetMapping("")
    public String findAllTagsAndClassification(
            Model model){
        model.addAttribute("classifications",classificationService.findAllClassification());
        model.addAttribute("tags",tagService.findAllTags());
        model.addAttribute("frontEndCommonsUtil",frontEndCommonsUtil);
        return "admin/category";
    }

    /**
     * 修改分类
     * @param classification
     * @return
     */
    @PutMapping("/modify")
    @ResponseBody
    @PreAuthorize("hasAuthority('xh:classification:update')")
    public Result modifyClassification(
            Classification classification
            ){

        classificationService.updateClassification(classification);
        return new Result(Code.UPDATE_SUCCESS_CODE, Message.UPDATE_SUCCESS_MSG);
    }

    /**
     * 新增分类
     * @param classification
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public Result saveClassification(
            Classification classification
    ){

        classificationService.saveClassification(classification);
        return new Result(Code.ADD_SUCCESS_CODE, Message.ADD_SUCCESS_MSG);
    }

    /**
     * 删除分类
     * @param classificationId  分类id
     * @param httpServletRequest
     * @return
     */
    @DeleteMapping("/classification-delete/{classificationId}")
    @ResponseBody
    @PreAuthorize("hasAuthority('xh:classification:delete')")
    public Result deleteClassification(
            @PathVariable
            Long classificationId,
            HttpServletRequest httpServletRequest
    ){

        classificationService.deleteClassificationByClassificationId(classificationId);
        logsService.addLog(LogActions.LOGS_ACTION_DELETE_CLASSIFICATION, classificationId + "", httpServletRequest.getRemoteAddr(), 1L);
        return new Result(Code.DELETE_SUCCESS_CODE, Message.DELETE_SUCCESS_MSG);
    }

    /**
     * 删除标签
     * @param tagId 标签id
     * @param httpServletRequest
     * @return
     */
    @DeleteMapping("/tag-delete/{tagId}")
    @ResponseBody
    @PreAuthorize("hasAuthority('xh:tag:delete')")
    public Result deleteTag(
            @PathVariable
            Long tagId,
            HttpServletRequest httpServletRequest
    ){

        tagService.deleteTagByTagId(tagId);
        logsService.addLog(LogActions.LOGS_ACTION_DELETE_TAG, tagId + "", httpServletRequest.getRemoteAddr(), 1L);
        return new Result(Code.DELETE_SUCCESS_CODE, Message.DELETE_SUCCESS_MSG);
    }

}

