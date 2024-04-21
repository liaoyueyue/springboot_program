package org.example.myojssm.controller;

import jakarta.validation.constraints.NotNull;
import org.example.myojssm.common.Result;
import org.example.myojssm.entity.Collection;
import org.example.myojssm.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Result addCollection(@RequestBody @Validated(Collection.Add.class) Collection collection) {
        return collectionService.addCollection(collection);
    }

    @GetMapping("/list")
    public Result getCollectionList(@NotNull Integer pageNum, @NotNull Integer pageSize, @RequestParam(required = false) String collectionName) {
        return collectionService.getCollectionList(pageNum, pageSize, collectionName);
    }

    @PostMapping("/update")
    public Result updateCollection(@RequestBody @Validated(Collection.Update.class) Collection collection) {
        return collectionService.updateCollection(collection);
    }
}
