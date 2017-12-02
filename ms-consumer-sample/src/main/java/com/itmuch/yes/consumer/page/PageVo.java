package com.itmuch.yes.consumer.page;

import lombok.Data;

@Data
public class PageVo {
    private int page;

    private int rows;
    /**
     * 排序字段
     */
    private String sort;

    /**
     * 排序方式ASC/DESC
     */
    private String order;
}
