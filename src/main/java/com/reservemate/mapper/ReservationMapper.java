package com.reservemate.mapper;

import com.reservemate.pojo.dto.ReservationRequest;
import com.reservemate.pojo.entity.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ReservationMapper {

    /** 统计冲突 */
    int countConflict(@Param("tableId") Long tableId,
                      @Param("start") LocalDateTime start,
                      @Param("end") LocalDateTime end);

    /** 插入新预定，返回影响行数，主键回填 reservationId */
    int insert(Reservation reservation);

    /** 查询指定日期 & 时段 已被预定的餐桌ID 列表 */
    List<Long> selectReservedTableIds(@Param("date") LocalDate date,
                                      @Param("start") LocalDateTime start);

    /** 查询所有需要标记为 completed 的预定ID */
    List<Long> selectToComplete();

    /** 批量把这些预定标记为 completed */
    int batchComplete(@Param("ids") List<Long> ids);

    /** 取消预定（逻辑删除） */
    int cancel(Reservation reservation);

    /** 查询某用户所有未取消且未完成的预定 */
    List<Reservation> selectByUser(@Param("userId") Long userId);

}
