package com.reservemate.service;

import com.reservemate.pojo.entity.TableInfo;

import java.util.List;

public interface TableInfoService {
    void add(TableInfo tableInfo);
    void delete(Long tableId);
    void update(TableInfo tableInfo);
    TableInfo getById(Long tableId);
    List<TableInfo> getAll();
}
