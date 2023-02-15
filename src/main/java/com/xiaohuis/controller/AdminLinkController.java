package com.xiaohuis.controller;


import com.github.pagehelper.PageInfo;
import com.xiaohuis.constant.Code;
import com.xiaohuis.constant.LogActions;
import com.xiaohuis.constant.Message;
import com.xiaohuis.entity.Link;
import com.xiaohuis.service.LinkService;
import com.xiaohuis.service.LogsService;
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
 *  管理员友链管理前端控制器
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
@Controller
@RequestMapping("/admin/links")
public class AdminLinkController {

    @Autowired
    private FrontEndCommonsUtil commons;


    @Autowired
    private LinkService linkService;

    @Autowired
    private LogsService logsService;

    /**
     * 查询所有友链，前往友链列表页面
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("")
    public String findAllLinkAndPage(
            @RequestParam(name = "page", required = false, defaultValue = "1")
            Integer pageNum,
            Model model){
        PageInfo<Link> links = linkService.findAllLinkAndPage(pageNum);
        model.addAttribute("links",links);
        model.addAttribute("commons",commons);
        return "admin/links";
    }

    /**
     * 新增友链
     * @param link
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public Result saveLink(Link link){

        linkService.saveLink(link);
        return new Result(Code.ADD_SUCCESS_CODE, Message.ADD_ERR_MSG);
    }

    /**
     * 修改友链
     * @param link
     * @return
     */
    @PutMapping("/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('xh:links:update')")
    public Result updateLink(Link link){

        linkService.updateLink(link);
        return new Result(Code.UPDATE_SUCCESS_CODE, Message.UPDATE_SUCCESS_MSG);
    }

    /**
     * 删除友链
     * @param id 友链id
     * @param httpServletRequest
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('xh:links:delete')")
    public Result deleteLinkById(
            @PathVariable
            Long id,
            HttpServletRequest httpServletRequest
    ){

        linkService.deleteLinkById(id);
        logsService.addLog(LogActions.LOGS_ACTION_DELETE_LINK, id + "", httpServletRequest.getRemoteAddr(), 1L);
        return new Result(Code.DELETE_SUCCESS_CODE, Message.DELETE_SUCCESS_MSG);
    }


}

