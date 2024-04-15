package org.example.myojssm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.myojssm.entity.Collection;

import java.util.List;

/**
 * @author liaoyueyuedescription
 * @description 针对表【collection】的数据库操作Mapper
 * @createDate 2024-03-31 12:32:23
 * @Entity org.example.myojssm.entity.Collection
 */
@Mapper
public interface CollectionMapper {

    /**
     * 新增一个题目合集
     *
     * @param collection 合集实体
     * @return 数据库影响行数
     */
    int insertCollection(Collection collection);

    /**
     * 查询合集列表
     *
     * @param collectionName 合集名称
     * @return 符合条件合集列表
     */
    List<Collection> queryCollectionListByName(@Param("collectionName") String collectionName);

    /**
     * 查询合集使用编号
     *
     * @param id 合集编号
     * @return 编号对应合集
     */
    Collection queryCollectionById(@Param("id") int id);

    /**
     * 更新合集使用编号
     *
     * @param collection 合集实体
     * @return 数据库影响行数
     */
    int updateCollectionById(Collection collection);

}




