package com.itmuch.yes.core.page;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageVoWithSort extends PageVo {

    /**
     * 排序字段
     */
    private String sort;

    /**
     * 排序方式ASC/DESC
     */
    private String order;

    public Sort getSpringSort() {
        if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
            return new Sort(
                    new Sort.Order(
                            this.getSpringDirection(),
                            this.sort
                    )
            );
        }
        return null;
    }

    private Sort.Direction getSpringDirection() {
        return Sort.Direction.fromString(this.order);
    }
}
