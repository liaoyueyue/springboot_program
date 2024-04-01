package org.example.myojssm.common.anno;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.example.myojssm.common.validation.LevelValidation;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 自定义注解题目等级
 * User: liaoyueyue
 * Date: 2024-04-01
 * Time: 23:31
 */
@Documented //元注解
@Constraint(validatedBy = {LevelValidation.class})  //提供指定校验规则的类
@Target({ElementType.PARAMETER, ElementType.FIELD}) //元注解
@Retention(RetentionPolicy.RUNTIME) //元注解
public @interface Level {
    // 校验失败提示信息
    String message() default "Level参数的值只能为简单, 中等, 困难";

    // 指定分组
    Class<?>[] groups() default {};

    // 负载 获取到Level注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
