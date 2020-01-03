package org.hk.compass.util;


import java.util.HashMap;
import java.util.Map;

/**
 * @author zengry
 * @description 自定义全局返回数据格式
 * @since 2019/12/27
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 3822262577606553405L;
    public R() {
        put("code", 0);
        put("msg", "success");
    }

    public static R error() {
        return error(500, "系统错误，请联系管理员");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        return new R().put("code", code).put("msg", msg);
    }

    public static R success(String msg) {
        return new R().put("msg", msg);
    }

    public static R success(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R success() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static R result(Boolean flag){
        return flag ? R.success() : R.error();
    }

}
