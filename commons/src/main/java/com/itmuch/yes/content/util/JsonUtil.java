package com.itmuch.yes.content.util;

import com.itmuch.yes.content.core.convert.AjaxResult;
import com.itmuch.yes.content.util.mapper.JsonMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class JsonUtil {
    public static void writeJson(ServletResponse response, AjaxResult ajaxResult) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.write(
                    JsonMapper.defaultMapper()
                            .toJson(ajaxResult)
            );
            out.flush();
            out.close();
        } catch (IOException e) {
            log.error("发生异常。", e);
        }
    }
}
