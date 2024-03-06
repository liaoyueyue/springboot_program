package com.example.danmudemo.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-06
 * Time: 11:09
 */
public class AjaxResult extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public AjaxResult() {
        put("code", 0);
        put("msg", "操作成功");
    }

    public static AjaxResult fail() {
        return fail(1, "操作失败");
    }

    public static AjaxResult fail(String msg) {
        return fail(500, msg);
    }

    public static AjaxResult fail(int code, String msg) {
        AjaxResult r = new AjaxResult();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static AjaxResult success(String msg) {
        AjaxResult r = new AjaxResult();
        r.put("msg", msg);
        return r;
    }

    public static AjaxResult success(Map<String, Object> map) {
        AjaxResult r = new AjaxResult();
        r.putAll(map);
        return r;
    }

    public static AjaxResult success() {
        return new AjaxResult();
    }

    @Override
    public AjaxResult put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
