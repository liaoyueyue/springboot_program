package com.example.myblogssm.controller;

import com.example.myblogssm.common.utils.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2023-11-17
 * Time: 20:52
 */

@RestController
@RequestMapping("/verify")
public class VerifyController {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 生成验证码的接口
     *
     * @param response Response对象
     * @param request  Request对象
     * @throws Exception
     */
    @PostMapping("/getcode")
    public void getCode(HttpServletResponse response, HttpServletRequest request) throws Exception {
        // 获取到session
        HttpSession session = request.getSession();
        // 取到sessionid
        String id = session.getId();

        // 利用图片工具生成图片
        // 返回的数组第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyUtil.newBuilder()
                .setWidth(105)   //设置图片的宽度
                .setHeight(40)   //设置图片的高度
                .setSize(6)      //设置字符的个数
                .setLines(5)    //设置干扰线的条数
                .setFontSize(25) //设置字体的大小
                .setTilt(true)   //设置是否需要倾斜
                .setBackgroundColor(Color.white) //设置验证码的背景颜色
                .build()         //构建VerifyUtil项目
                .createImage();  //生成图片
        // 将验证码存入Session
        session.setAttribute("SESSION_VERIFY_CODE_" + id, objs[0]);
        // 打印验证码
        System.out.println(objs[0]);

        // 设置redis值的序列化方式
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // 在redis中保存一个验证码最多尝试次数
        // 这里采用的是先预设一个上限次数，再以reidis decrement(递减)的方式来进行验证
        // 这样有个缺点，就是用户只申请验证码，不验证就走了的话，这里就会白白占用5分钟的空间，造成浪费了
        // 为了避免以上的缺点，也可以采用redis的increment（自增）方法，只有用户开始在做验证的时候设置值，
        //    超过多少次错误，就失效；避免空间浪费
        redisTemplate.opsForValue().set(("VERIFY_CODE_" + id), "3", 5 * 60, TimeUnit.SECONDS);

        // 将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }

}
