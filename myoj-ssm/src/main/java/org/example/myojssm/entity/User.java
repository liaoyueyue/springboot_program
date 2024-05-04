package org.example.myojssm.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @TableName user
 */
@Data
@AllArgsConstructor
public class User implements Serializable {
    @NotNull(groups = {User.Update.class})
    private Integer id;
    private String username;
    @JsonIgnore
    private String password;
    @NotEmpty(groups = {User.Update.class})
    @Pattern(regexp = "^\\S{2,25}$")
    private String nickname;
    @NotNull(groups = {User.Update.class})
    @Email
    private String email;
    private String userPic;
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;
    @JsonIgnore
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;
    private static final long serialVersionUID = 1L;

    public interface Add{

    }

    public interface Update{

    }
}