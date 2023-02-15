package com.xiaohuis.controller;

import com.github.pagehelper.PageInfo;
import com.xiaohuis.api.QiNiuCloudService;
import com.xiaohuis.constant.*;
import com.xiaohuis.entity.Files;
import com.xiaohuis.pojo.UserVo;
import com.xiaohuis.service.FilesService;
import com.xiaohuis.service.LogsService;
import com.xiaohuis.util.CommonUtil;
import com.xiaohuis.util.FileResult;
import com.xiaohuis.util.FrontEndCommonsUtil;
import com.xiaohuis.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p> 文件管理controller </p>
 *
 * @author xiaohui
 * @description
 * @date 2022/11/21 14:52
 */
@Controller
@RequestMapping("/admin/files")
public class AdminFileController {

    @Autowired
    private FrontEndCommonsUtil commons;

    @Autowired
    public FilesService filesService;

    @Autowired
    public LogsService logsService;


    /**
     * 查询所有文件，前往文件列表页面
     * @param pageNum
     * @param model
     * @return
     */
    @GetMapping("")
    public String findAllFilesByPage(
            @RequestParam(name = "page", required = false, defaultValue = "1")
            Integer pageNum,
            Model model){
        PageInfo<Files> fileList = filesService.findAllFilesByPage(pageNum);
        model.addAttribute("fileList",fileList);
        model.addAttribute("commons",commons);
        return "admin/files";
    }

    /**
     * 上传单个文件
     * @param file
     * @return
     */
    @PostMapping("/uploadfile")
    @ResponseBody
    public FileResult uploadFileToQiNiu(
            @RequestParam(name = "editormd-image-file", required = true)
            MultipartFile file,
            HttpServletResponse httpServletResponse
    ) {

        String fileName = null;
        if(!file.isEmpty()){
            try {

                fileName = CommonUtil.getFileKey(file.getOriginalFilename().replaceFirst("/", ""));

                QiNiuCloudService.upload(file, fileName);

            }catch (Exception e){
                return new FileResult(Code.UPLOAD_FILE_ERR_CODE, Message.UPLOAD_FILE_ERR_MSG);
            }
        }
        httpServletResponse.setHeader("X-Frame-Options", "SAMEORIGIN");
        return new FileResult(Code.UPLOAD_FILE_SUCCESS_CODE, Message.UPLOAD_FILE_SUCCESS_MSG,"https://file.xiaohuis.top/"+fileName);
    }


    /**
     * 上传多个文件
     * @param files
     * @return
     */
    @PostMapping("/upload-files")
    @ResponseBody
    public FileResult uploadFilesToQiNiu(
            @RequestParam(name = "file", required = true)
            MultipartFile[] files,
            HttpSession httpSession
    ) {

        String fileName = null;
        Files files1;
        for(MultipartFile file : files){
                try {

                    UserVo userVo = (UserVo) httpSession.getAttribute(SystemConstant.LOGIN_SESSION_KEY);
                    Long userId = userVo.getId();
                    fileName = CommonUtil.getFileKey(file.getOriginalFilename().replaceFirst("/", ""));

                    QiNiuCloudService.upload(file, fileName);

                    files1 = new Files();
                    files1.setFileName(fileName);
                    files1.setFileType(CommonUtil.isImage(file.getInputStream()) ? OtherCommonConstants.FILE_TYPE_IMAGE : OtherCommonConstants.FILE_TYPE_FILE);
                    files1.setFileKey(fileName);
                    files1.setUserId(userId);
                    files1.setCreateTime(new Date());
                    filesService.saveFiles(files1);

                }catch (Exception e){
                    return new FileResult(Code.UPLOAD_FILE_ERR_CODE, Message.UPLOAD_FILE_ERR_MSG);
                }
        }

        return new FileResult(Code.UPLOAD_FILE_SUCCESS_CODE, Message.UPLOAD_FILE_SUCCESS_MSG);

    }

    /**
     * 删除文件
     * @param id 文件id
     * @param httpServletRequest
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    @PreAuthorize("hasAuthority('xh:files:delete')")
    public Result deleteLinkById(
            @PathVariable
            Long id,
            HttpServletRequest httpServletRequest,
            HttpSession httpSession
    ){
        filesService.deleteFilesById(id);
        UserVo userVo = (UserVo) httpSession.getAttribute(SystemConstant.LOGIN_SESSION_KEY);
        Long userId = userVo.getId();
        logsService.addLog(LogActions.LOGS_ACTION_DELETE_FILE, id + "", httpServletRequest.getRemoteAddr(), userId);
        return new Result(Code.DELETE_SUCCESS_CODE, Message.DELETE_SUCCESS_MSG);
    }

}
