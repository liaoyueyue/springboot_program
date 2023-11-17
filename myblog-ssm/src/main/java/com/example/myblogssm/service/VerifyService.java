package com.example.myblogssm.service;

import com.example.myblogssm.common.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-11-17
 * Time: 22:47
 */
@Service
public class VerifyService {
    @Autowired
    RedisTemplate redisTemplate;
    /**
     * 业务接口包含了验证码的验证
     * -1 验证码失效
     *  0 验证码错误
     *  1 验证码正确
     * @param code    前端传入的验证码
     * @param request Request对象
     * @return 验证码状态
     */
    public int checkCode(String code, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = session.getId();
        // 将redis中的尝试次数减一
        String verifyCodeKey = "VERIFY_CODE_" + id;
        long num = redisTemplate.opsForValue().decrement(verifyCodeKey);
        // 如果次数次数小于0 说明验证码已经失效
        if (num < 0) {
            return -1;
        }
        // 将session中的取出对应session id生成的验证码
        String serverCode = (String) session.getAttribute("SESSION_VERIFY_CODE_" + id);
        // 校验验证码
        if (null == serverCode || null == code || !serverCode.toUpperCase().equals(code.toUpperCase())) {
            return 0; // 验证码错误
        }
        // 验证通过之后手动将验证码失效
        redisTemplate.delete(verifyCodeKey);
        // 验证码正确
        return 1;
    }
}
