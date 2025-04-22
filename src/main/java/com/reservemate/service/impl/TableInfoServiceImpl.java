package com.reservemate.service.impl;

import com.reservemate.mapper.TableInfoMapper;
import com.reservemate.pojo.entity.TableInfo;
import com.reservemate.service.TableInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TableInfoServiceImpl implements TableInfoService {

    @Autowired
    private TableInfoMapper tableInfoMapper;

    @Override
    public void add(TableInfo tableInfo) {
        tableInfoMapper.insert(tableInfo);
    }

    @Override
    public void delete(Long tableId) {
        tableInfoMapper.deleteById(tableId);
    }

    @Override
    public void update(TableInfo tableInfo) {
        tableInfoMapper.update(tableInfo);
    }

    @Override
    public TableInfo getById(Long tableId) {
        return tableInfoMapper.selectById(tableId);
    }

    @Override
    public List<TableInfo> getAll() {
        return tableInfoMapper.selectAll();
    }
}
