package org.example.myojssm.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.myojssm.common.anno.Level;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-01
 * Time: 23:39
 */
public class LevelValidation implements ConstraintValidator<Level, String> {
    /**
     * 判断是否有效的
     * @param s 将要校验的数据
     * @param constraintValidatorContext 评估约束的上下文
     * @return 是否校验通过
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        // 提供校验规则
        if (s == null) {
            return false;
        }
        if (s.equals("简单") || s.equals("中等") || s.equals("困难") ) {
            return true;
        }
        return false;
    }
}
