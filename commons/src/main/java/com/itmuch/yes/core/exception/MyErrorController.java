package com.itmuch.yes.core.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itmuch.yes.core.convert.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 统一异常管理
 */
@Controller
@RequestMapping("/error")
public class MyErrorController extends BasicErrorController {

    @Autowired
    public MyErrorController(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        super(errorAttributes, serverProperties.getError());
    }

    @RequestMapping(produces = "text/html")
    @Override
    public ModelAndView errorHtml(HttpServletRequest request,
                                  HttpServletResponse response) {
        return super.errorHtml(request, response);
    }

    @RequestMapping
    @ResponseBody
    @Override
    @SuppressWarnings("unchecked")
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = super.getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));

        String message = body.get("message") != null ? (String) body.get("message") : null;
        Integer statusCode = body.get("status") != null ? (Integer) body.get("status") : null;

        Object exception = body.get("exception");
        if (exception != null && exception instanceof BizRuntimeException) {
            BizRuntimeException bre = (BizRuntimeException) exception;
            statusCode = bre.getCode();
        }

        AjaxResult<Object> build = AjaxResult.builder()
                .status(statusCode)
                .error(message)
                .message(message)
                .data(body)
                .build();
        Map res = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String s = mapper.writeValueAsString(build);
            res = mapper.readValue(s, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpStatus status = super.getStatus(request);
        return new ResponseEntity<Map<String, Object>>(res, status);
    }
}
