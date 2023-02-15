package com.xiaohuis.controller;

import com.xiaohuis.constant.OtherCommonConstants;
import com.xiaohuis.entity.Blog;
import com.xiaohuis.pojo.BlogCombination;
import com.xiaohuis.service.BlogService;
import com.xiaohuis.service.ClassificationService;
import com.xiaohuis.service.CommentService;
import com.xiaohuis.service.TagService;
import com.xiaohuis.util.MarkdownUtil;
import com.xiaohuis.util.RedisCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前台博客前端控制器
 * </p>
 *
 * @author xiaohui
 * @since 2022-10-16
 */
@Controller
public class BlogController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private ClassificationService classificationService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    private RedisCache redisCache;

    /**
     * 首页
     * @param pageNum 页码
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
            Integer pageNum,
            Model model

    ){
        model.addAttribute("blogs",blogService.findBlogCombinationByPaging(pageNum));
        model.addAttribute("classifications",classificationService.findClassificationByPaging());
        model.addAttribute("tags",tagService.findTagByPage());
        model.addAttribute("recommendedBlogs",blogService.findBlogByRecommendAndPaging(1,1));
        return "blog/index";
    }

    /**
     * 首页搜索功能
     * @param conditions 条件
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("/search")
    public String indexSearch(@RequestParam(required = false) String conditions,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                              Integer pageNum,
                              Model model){
        model.addAttribute("blogs",blogService.searchBlogByConditionsAndPaging(conditions,pageNum,1));
        return "blog/search";
    }

    /**
     * 进入博客详情页
     * @param id blogId作为条件
     * @param model
     * @return
     */
    @GetMapping("/blogDetails/{id}")
    public String blogDetails(
            @PathVariable
            Long id,
            Model model
    ){
        blogService.updateBlogViews(id);
        BlogCombination blogCombination = blogService.findBlogCombinationByBlogId(id);
        blogCombination.setContent(MarkdownUtil.markdownToHtmlExtens(blogCombination.getContent()));
        model.addAttribute("blog",blogCombination);
        model.addAttribute("commentMap",commentService.findCommentsByBlogId(id));
        return "blog/blog_details";
    }

    /**
     * 进入博客归档页
     * @param model
     * @return
     */
    @GetMapping("/archives")
    @ResponseBody
    public String blogArchive(
            Model model,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ){
        String HTML = redisCache.getString(OtherCommonConstants.ARCHIVES_REDIS_KEY);
        if (!StringUtils.isBlank(HTML)) {
            return HTML;
        }
        Map<String, List<Blog>> blogMap = blogService.findBlogByCreatTime();
        model.addAttribute("blogMap",blogMap);
        model.addAttribute("blogCount",blogService.findBlogCount());
        //如果为空 渲染页面 并且存入redis
        WebContext webContext = new WebContext(httpServletRequest, httpServletResponse, httpServletRequest.getServletContext(), httpServletRequest.getLocale(), model.asMap());
        //去渲染页面 页面需要模板的名称 用来以后调用  还需要IContext 上面获得IContext 传入
        HTML = thymeleafViewResolver.getTemplateEngine().process("blog/archives", webContext);
        if (!StringUtils.isBlank(HTML)) {
            // 丢入redis缓存
            redisCache.setString(OtherCommonConstants.ARCHIVES_REDIS_KEY, HTML, 30L, TimeUnit.MINUTES);
        }

        return HTML ;
    }

    /**
     * 跳转到about页面
     * @param model
     * @return
     */
    @GetMapping("/about")
    public String about(Model model){
        return "blog/about";
    }



}

