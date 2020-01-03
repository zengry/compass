package org.hk.compass.util;

import org.apache.commons.lang3.StringUtils;
import org.hk.compass.exception.CommonException;

/**
 * @author zengry
 * @description 数据校验 - 断言
 * @since 2019/12/31
 */
public abstract class Assert {
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new CommonException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (null == object) {
            throw new CommonException(message);
        }
    }
}
