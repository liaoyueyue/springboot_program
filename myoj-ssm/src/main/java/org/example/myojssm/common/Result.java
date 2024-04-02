package org.example.myojssm.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description: 统一数据格式返回
 * User: liaoyueyue
 * Date: 2024-2-29
 * Time: 22:07
 */
@AllArgsConstructor
@Data
public class Result {
    // 状态码
    private Integer code;
    // 状态码描述信息
    private String msg;
    // 返回的数据
    private Object data;

    /**
     * 返回成功结果-带响应数据
     *
     * @param data 操作成功响应数据
     * @return 响应数据
     */
    public static Result success(Object data) {
        return new Result(0, "Operation successful", data);
    }

    /**
     * 返回成功结果
     *
     * @return 响应成功状态
     */
    public static Result success() {
        return new Result(0, "Operation successful", null);
    }

    /**
     * 返回失败结果-自定义错误信息
     *
     * @param msg 状态码描述信息
     * @return 返回的数据
     */
    public static Result fail(String msg) {
        return new Result(-1, msg, null);
    }

    /**
     * 返回失败结果
     *
     * @return 返回的数据
     */
    public static Result fail() {
        return new Result(-1, "illegal request", null);
    }

}
