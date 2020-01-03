package org.hk.compass.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zengry
 * @description
 * @since 2020/1/2
 */
public class BeanCopyUtils {
    private BeanCopyUtils() {
    }
    public static void copyPropertiesIgnoreNull(Object orignSVO, Object destSVO) throws Exception {
        /* 1.源对象与目标对象都不能为空 */
        if (destSVO == null || orignSVO == null) {
            throw new Exception("拷贝属性值出错:源对象为空或目标对象为空");
        }

        /* 2.深度拷贝 */
        try {
            List<String> ignoreProperties = new ArrayList<>();
            ignoreProperties.addAll(Arrays.asList(getNullPropertyNames(orignSVO)));
            ignoreProperties.add("objectType");
            BeanUtils.copyProperties(orignSVO, destSVO, ignoreProperties.toArray(new String[ignoreProperties.size()]));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static boolean hasMethod(Object obj, String methodName) {
        if (!StringUtils.isBlank(methodName)) {
            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (methodName.equals(method.getName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);

        // 获取source 类 + 字段属性名
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null

                // todo 是否前端加过滤器
                // 过滤String 空字符串
                || ("java.lang.String".equals(pd.getPropertyType().getName()) && (StringUtils.equals("", (String)srcValue))))
                emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }



    /**
     * 根据字段名称取值
     *
     * @param obj
     * @param fieldName
     * @return
     */
    private static Object getClassValue(Object obj, String fieldName) throws Exception {
        if (obj == null) {
            return null;
        }
        try {
            @SuppressWarnings("rawtypes")
            Class beanClass = obj.getClass();
            Method[] ms = beanClass.getMethods();
            for (int i = 0; i < ms.length; i++) {
                // 非get方法不取
                if (!ms[i].getName().startsWith("get")) {
                    continue;
                }
                Object objValue = null;
                try {
                    objValue = ms[i].invoke(obj, new Object[] {});
                } catch (Exception e) {
                    System.out.println("反射取值出错：" + e.toString());
                    continue;
                }
                if (objValue == null) {
                    continue;
                }
                if (ms[i].getName().toUpperCase().equals(fieldName.toUpperCase())
                    || ms[i].getName().substring(3).toUpperCase().equals(fieldName.toUpperCase())) {
                    return objValue;
                } else if (fieldName.toUpperCase().equals("SID") && (ms[i].getName().toUpperCase().equals("ID")
                    || ms[i].getName().substring(3).toUpperCase().equals("ID"))) {
                    return objValue;
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

}
