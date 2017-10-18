package com.ctw.plugins.spring;

import com.ctw.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ctw
 *
 * @Author: HaiAng
 * @Time： 2016/5/27.13:36
 * @Vistion：1.0
 * @Remark： 数据可是统一配置
 */

public class DateConverter implements Converter<String, Date> {

    private static final Logger logger = LoggerFactory.getLogger(DateConverter.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final int DATE_LENGTH = 10;
    private static final int TIME_LENGTH = 8;
    private static final int DATETIME_LENGTH = 19;

    private final static ThreadLocal<SimpleDateFormat> DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_FORMAT);
        }
    };
    private final static ThreadLocal<SimpleDateFormat> TIME_FORMAT_THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(TIME_FORMAT);
        }
    };
    private final static ThreadLocal<SimpleDateFormat> DATETIME_FORMAT_THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATETIME_FORMAT);
        }
    };

    public Date convert(String date) {
        Date result = null;
        if (StringUtils.isNotBlank(date)) {
            String datestr = date.trim();
            Calendar cal = Calendar.getInstance();
            if (StringUtils.isNumeric(datestr)) {
                // long类型数值
                cal.setTimeInMillis(Long.parseLong(datestr));
                result = cal.getTime();
            } else if (datestr.length() <= TIME_LENGTH) { // 时间格式 时分秒
                result = parseDate(TIME_FORMAT_THREAD_LOCAL, datestr);
            } else if (datestr.length() <= DATE_LENGTH) { // 日期格式 年月日
                result = parseDate(DATE_FORMAT_THREAD_LOCAL, datestr);
            } else if (datestr.length() <= DATETIME_LENGTH) { // 完整格式 年月日时分秒
                result = parseDate(DATETIME_FORMAT_THREAD_LOCAL, datestr);
            } else { // 不支持的日期格式
                throw new BusinessException("日期格式不正确！");
            }
        }
        return result;
    }

    private Date parseDate(final ThreadLocal<SimpleDateFormat> threadLocal, String datestr) {
        DateFormat df = threadLocal.get();
        Date date;
        try {
            date = df.parse(datestr);
        } catch (ParseException pe) {
            logger.warn(">> failed to convert value [" + datestr + "] to java.util.Date");
            throw new BusinessException("日期格式不正确！");
        }
        return date;
    }
}
