package com.nh.limit.common.converter;

import com.nh.limit.common.utils.DateUtil;
import com.wuwenze.poi.convert.WriteConverter;
import com.wuwenze.poi.exception.ExcelKitWriteConverterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ValueOperations;

import java.text.ParseException;

/**
 * @Classname TimeConverter
 * @Description TODO 时间转换器
 * @Date 2019/10/12 10:19 AM
 * @Created by nihui
 */
@Slf4j
public class TimeConverter implements WriteConverter {
    @Override
    public String convert(Object value)  {
        if (value==null){
            return "";
        }else {
            try{
                return DateUtil.formatCSTTime(value.toString(),DateUtil.FULL_TIME_SPLIT_PATTERN);
            }catch (ParseException e){
                String message = "时间格式转换异常";
                log.error(message,e);
                throw new ExcelKitWriteConverterException(message);
            }
        }
    }
}
