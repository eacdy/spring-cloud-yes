package com.itmuch.yes.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class DateTimeUtil implements Serializable {

    public static Date parseToDate(String stringDate) {
        if (StringUtils.isBlank(stringDate)) {
            return new Date();
        }
        List<String> patterns = Stream.of(
                "yyyy年MM月dd日",
                "yy-MM",
                "yyyy-MM",
                "yyyy-MM-dd",
                "yyyy/MM/dd",
                "yyyy-MM-dd HH:mm:ss",
                "yyyy-MM-dd HH:mm",
                "yyyy-MM-dd HH"
        ).collect(Collectors.toList());

        for (String pattern : patterns) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            try {
                return df.parse(stringDate);
            } catch (Exception ignored) {
            }
        }
        log.error("转换日期发生异常，没有适配的pattern。日期为：{}", stringDate);
        return null;
    }

}