package com.reservemate.mapper;

import com.reservemate.pojo.entity.TableInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TableInfoMapper {

    @Insert("INSERT INTO table_info(" +
            "table_name, capacity, location, status, photo_url" +
            ") VALUES(" +
            "#{tableName}, #{capacity}, #{location}, #{status}, #{photoUrl}" +
            ")")
    @Options(useGeneratedKeys = true, keyProperty = "tableId", keyColumn = "table_id")
    int insert(TableInfo tableInfo);

    @Delete("DELETE FROM table_info WHERE table_id = #{tableId}")
    int deleteById(@Param("tableId") Long tableId);

    @Update("UPDATE table_info SET " +
            "table_name = #{tableName}, " +
            "capacity   = #{capacity}, " +
            "location   = #{location}, " +
            "status     = #{status}, " +
            "photo_url  = #{photoUrl} " +
            "WHERE table_id = #{tableId}")
    int update(TableInfo tableInfo);

    @Select("SELECT * FROM table_info WHERE table_id = #{tableId}")
    TableInfo selectById(@Param("tableId") Long tableId);

    @Select("SELECT * FROM table_info ORDER BY table_id")
    List<TableInfo> selectAll();
}
