package org.example.myojssm.controller;

import org.example.myojssm.common.Result;
import org.example.myojssm.entity.Collection;
import org.example.myojssm.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: liaoyueyue
 * Date: 2024-03-31
 * Time: 12:39
 */
@RestController
@RequestMapping("/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    @PostMapping("/add")
    public Result addCollection(@Validated(Collection.Add.class) Collection collection) {
        return collectionService.addCollection(collection);
    }

    @GetMapping("/list")
    public Result getCollectionList() {
        return collectionService.getCollectionList();
    }

    @PostMapping("/update")
    public Result updateCollection(@Validated(Collection.Update.class) Collection collection) {
        return collectionService.updateCollection(collection);
    }
}
