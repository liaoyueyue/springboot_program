package org.example.myojssm.service.impl;

import org.example.myojssm.common.Result;
import org.example.myojssm.entity.Collection;
import org.example.myojssm.mapper.CollectionMapper;
import org.example.myojssm.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Result getCollectionList() {
        return Result.success(collectionMapper.queryAllCollection());
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
