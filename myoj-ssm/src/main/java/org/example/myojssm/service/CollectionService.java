package org.example.myojssm.service;

import org.example.myojssm.common.Result;
import org.example.myojssm.entity.Collection;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-31
 * Time: 12:42
 */
public interface CollectionService {
    /**
     * 新增一个合集
     * @param collection 合集实体
     * @return 是否成功添加
     */
    Result addCollection(Collection collection);

    /**
     * 获取合集列表
     * @return 所有合集列表
     */
    Result getCollectionList();

    /**
     * 查询合集使用编号
     * @param id 合集编号
     * @return 编号对应合集
     */
    Result getCollectionById(int id);

    /**
     * 更新合集使用编号
     * @param collection 合集实体
     * @return 是否更新成功
     */
    Result updateCollection(Collection collection);
}
