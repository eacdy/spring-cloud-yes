package com.itmuch.yes.core.page;

import lombok.Data;

@Data
public class PageVo {
    public static final int MAX_ROWS_PER_PAGE = 100;
    public static final int DEFAULT_ROWS_PER_PAGE = 10;
    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 单页显示多少条
     */
    private Integer rows = DEFAULT_ROWS_PER_PAGE;

    /**
     * 最多显示50条
     *
     * @return 单页显示多少条
     */
    public Integer getRows() {
        if (this.rows > MAX_ROWS_PER_PAGE) {
            this.rows = MAX_ROWS_PER_PAGE;
        } else if (this.rows <= 0) {
            this.rows = DEFAULT_ROWS_PER_PAGE;
        }
        return this.rows;
    }
}
