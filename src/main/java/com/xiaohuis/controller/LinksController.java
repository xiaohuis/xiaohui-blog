package com.xiaohuis.controller;


import com.xiaohuis.constant.OtherCommonConstants;
import com.xiaohuis.service.LinkService;
import com.xiaohuis.util.RedisCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前台友链查询前端控制器
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
@Controller
public class LinksController {

    @Autowired
    private LinkService linkService;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    private RedisCache redisCache;

    /**
     * 友链分页查询页面，查询所有友链
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/links")
    @ResponseBody
    public String findClassifications(
                           @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                           int pageNum,
                           Model model,
                           HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse
    ){
        String HTML = redisCache.getString(OtherCommonConstants.LINKS_REDIS_KEY);
        if (!StringUtils.isBlank(HTML)) {
            return HTML;
        }
        model.addAttribute("links", linkService.findAllLinkAndPage(pageNum));
        //如果为空 渲染页面 并且存入redis
        WebContext webContext = new WebContext(httpServletRequest, httpServletResponse, httpServletRequest.getServletContext(), httpServletRequest.getLocale(), model.asMap());
        //去渲染页面 页面需要模板的名称 用来以后调用  还需要IContext 上面获得IContext 传入
        HTML = thymeleafViewResolver.getTemplateEngine().process("blog/links", webContext);
        if (!StringUtils.isBlank(HTML)) {
            // 丢入redis缓存
            redisCache.setString(OtherCommonConstants.LINKS_REDIS_KEY, HTML, 30L, TimeUnit.MINUTES);
        }

        return HTML ;
    }

}

