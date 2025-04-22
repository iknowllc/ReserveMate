package com.reservemate.controller;

import com.reservemate.pojo.entity.TableInfo;
import com.reservemate.pojo.vo.Result;
import com.reservemate.service.TableInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/table")
public class TableInfoController {

    @Autowired
    private TableInfoService tableInfoService;

    /** 查询所有餐桌 */
    @GetMapping("/list")
    public Result<List<TableInfo>> getAllTables() {
        List<TableInfo> list = tableInfoService.getAll();
        return Result.success(list);
    }

    /** 根据 tableId 查询餐桌详情 */
    @GetMapping("/{tableId}")
    public Result<TableInfo> getTableById(@PathVariable Long tableId) {
        TableInfo table = tableInfoService.getById(tableId);
        return table != null
                ? Result.success(table)
                : Result.failure("餐桌不存在");
    }

    /** 添加餐桌 */
    @PostMapping("/add")
    public Result<Void> addTable(@RequestBody TableInfo tableInfo) {
        tableInfoService.add(tableInfo);
        return Result.success();
    }

    /** 修改餐桌 */
    @PutMapping("/update")
    public Result<Void> updateTable(@RequestBody TableInfo tableInfo) {
        tableInfoService.update(tableInfo);
        return Result.success();
    }

    /** 删除餐桌 */
    @DeleteMapping("/delete/{tableId}")
    public Result<Void> deleteTable(@PathVariable Long tableId) {
        tableInfoService.delete(tableId);
        return Result.success();
    }
}
