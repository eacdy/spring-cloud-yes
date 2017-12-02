package com.itmuch.yes.core.convert;

import com.itmuch.yes.core.constant.ConstantsCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * AjaxResult，兼容spring boot原生的消息提示
 *
 * @param <T> T
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class AjaxResult<T> {
    private Integer status = ConstantsCode.SUCCESS_CODE;
    private String error = "成功";
    private String message = "操作成功";
    private T data = null;

    public AjaxResult<T> success(String error, String message) {
        this.error = error;
        this.message = message;
        return this;
    }

    public AjaxResult<T> success(T data) {
        this.data = data;
        return this;
    }

    public AjaxResult<T> success() {
        return this;
    }

    public AjaxResult<T> fail(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
        return this;
    }
}