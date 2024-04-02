package org.example.myojssm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-04-02
 * Time: 22:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {
    private Long total;
    private List<T> items;
}


