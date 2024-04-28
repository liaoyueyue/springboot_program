package org.example.myojssm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.example.myojssm.common.anno.Level;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description: OJ 题
 * User: liaoyueyue
 * Date: 2024-01-14
 * Time: 14:44
 */
@Data
public class Problem implements Serializable {
    @NotNull(groups = Problem.Update.class)
    private Integer id;
    @NotEmpty(groups = {Problem.Add.class, Problem.Update.class})
    @Pattern(regexp = "^\\S{2,12}$")
    private String title;
    @Level
    private String level;
    @NotNull(groups = {Problem.Add.class, Problem.Update.class})
    private Integer collectionId;
    @NotEmpty(groups = {Problem.Add.class, Problem.Update.class})
    private String description;
    @NotEmpty(groups = {Problem.Add.class, Problem.Update.class})
    private String templateCode;
    @NotEmpty(groups = {Problem.Add.class, Problem.Update.class})
    private String testCode;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    private static final long serialVersionUID = 1L;

    public interface Add{

    }

    public interface Update{

    }
}
