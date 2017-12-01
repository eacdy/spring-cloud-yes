package com.itmuch.cloud.study.user.controller;


import com.itmuch.cloud.study.user.feign.UploadFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ConsumerUploadController {
    @Autowired
    private UploadFeignClient uploadFeignClient;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String handleFileUpload(@RequestPart(value = "file") MultipartFile file) throws IOException {
        return this.uploadFeignClient.handleFileUpload(file);
    }
}
