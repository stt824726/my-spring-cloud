package com.stt.common.util;

import com.stt.constant.ResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Slf4j
public class ConvertUtil {

    public static <T> T convert(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ResultException(e.getMessage());
        }

        BeanUtils.copyProperties(source, t);
        return t;
    }

    public static <T> List<T> convertList(List<?> sources, Class<T> clazz) {
        if (sources == null) {
            return null;
        }
        List<T> result = new ArrayList<>(sources.size());
        for (Object source : sources) {
            T t = null;
            try {
                t = clazz.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new ResultException(e.getMessage());
            }

            BeanUtils.copyProperties(source, t);
            result.add(t);
        }

        return result;
    }

}
