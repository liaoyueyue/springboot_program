package org.example.myojssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.myojssm.common.Result;
import org.example.myojssm.entity.Collection;
import org.example.myojssm.entity.PageBean;
import org.example.myojssm.mapper.CollectionMapper;
import org.example.myojssm.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-31
 * Time: 12:42
 */
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public Result addCollection(Collection collection) {
        return collectionMapper.insertCollection(collection) > 0 ? Result.success() : Result.fail();
    }

    @Override
    public Result getCollectionList(Integer pageNum, Integer pageSize, String collectionName) {
        if (pageNum < 1 || pageSize > 5) {
            return Result.fail("Illegal parameters");
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Collection> collections = collectionMapper.queryCollectionListByName(collectionName);
        Page<Collection> page = (Page<Collection>) collections;
        return Result.success(new PageBean<>(page.getTotal(), page.getResult()));
    }

    @Override
    public Result getCollectionById(int id) {
        Collection collection = collectionMapper.queryCollectionById(id);
        if (collection == null) {
            return Result.fail("Classification not found");
        }
        return Result.success(collection);
    }

    @Override
    public Result updateCollection(Collection collection) {
        return collectionMapper.updateCollectionById(collection)>0?Result.success():Result.fail();
    }
}
