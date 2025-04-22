package com.reservemate.mapper;

import com.reservemate.pojo.entity.Announcement;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AnnouncementMapper {

    @Insert("INSERT INTO announcement(title, content) VALUES(#{title}, #{content})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Announcement announcement);

    @Delete("DELETE FROM announcement WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @Update("UPDATE announcement SET title = #{title}, content = #{content} WHERE id = #{id}")
    int update(Announcement announcement);

    /** 查询所有公告 */
    @Select("SELECT id, title, content FROM announcement")
    List<Announcement> selectAll();

    /** 根据 ID 查询公告 */
    @Select("SELECT id, title, content FROM announcement WHERE id = #{id}")
    Announcement selectById(@Param("id") Long id);
}
