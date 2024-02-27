package org.example.myojssm.entity;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-01-11
 * Time: 17:44
 */
@Data
public class Answer {
    private int error; // 错误码：0 编译运行成功；1 编译出错；2 运行出错；
    private String reason; // 出错的提示信息
    private String stdout; // 运行程序得到的标准输出的结果
    private String stderr; // 运行程序得到的标准异常结果
}
