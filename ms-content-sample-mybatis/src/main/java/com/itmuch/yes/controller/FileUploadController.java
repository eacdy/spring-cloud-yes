package com.itmuch.yes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @author itmuch.com
 */
@Controller
public class FileUploadController {
    /**
     * 上传文件
     * 测试方法：
     * 使用命令：curl -F "file=@文件全名" localhost:8050/upload
     * ps.该示例比较简单，没有做IO异常、文件大小、文件非空等处理
     *
     * @param file 待上传的文件
     * @return 文件在服务器上的绝对路径
     * @throws IOException IO异常
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestPart(value = "file") MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        File fileToSave = new File(
                Objects.requireNonNull(file.getOriginalFilename())
        );
        FileCopyUtils.copy(bytes, fileToSave);
        return fileToSave.getAbsolutePath();
    }
}