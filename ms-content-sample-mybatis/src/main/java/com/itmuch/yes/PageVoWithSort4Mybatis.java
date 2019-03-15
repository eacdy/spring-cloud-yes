package com.itmuch.yes;

import com.itmuch.yes.core.page.PageVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageVoWithSort4Mybatis extends PageVo {
    /**
     * 排序字段
     */
    private String sort;
}
