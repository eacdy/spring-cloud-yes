package com.itmuch.yes.core.exception;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 统一异常管理
 */
@Controller
@RequestMapping("/error")
@Slf4j
public class MyErrorController extends BasicErrorController {

    public static final String ERROR_ATTRIBUTE = DefaultErrorAttributes.class.getName() + ".ERROR";

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

        log.debug("原始消息体：{}", body);

        String message = body.get("message") != null ? (String) body.get("message") : null;
        String error = body.get("error") != null ? (String) body.get("error") : null;

        Integer statusCode = body.get("status") != null ? (Integer) body.get("status") : null;

        Throwable exception = this.getException(request);

        // 如果是业务异常，就进行处理，使用自己定义的错误码
        if (exception instanceof BizRuntimeException) {
            BizRuntimeException bre = (BizRuntimeException) exception;
            statusCode = bre.getCode();
        }

        Map<String, Object> res = Maps.newHashMap();
        res.put("status", statusCode);
        res.put("error", error);
        res.put("message", message);
        res.put("data", body);

        HttpStatus status = super.getStatus(request);
        return new ResponseEntity<>(res, status);
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

    private Throwable getException(HttpServletRequest request) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        Throwable exception = getAttribute(
                requestAttributes,
                ERROR_ATTRIBUTE
        );

        if (exception == null) {
            exception = getAttribute(requestAttributes, "javax.servlet.error.exception");
        }
        return exception;
    }
}
