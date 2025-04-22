
package com.reservemate.service.impl;

import com.reservemate.common.context.UserContext;
import com.reservemate.mapper.ReservationMapper;
import com.reservemate.pojo.dto.ReservationRequest;
import com.reservemate.pojo.entity.Reservation;
import com.reservemate.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationMapper mapper;
    /** 2 小时固定间隔列表 */
    private static final int SLOT_MINUTES = 120;
    private static final LocalTime START = LocalTime.of(8,0);
    private static final LocalTime END   = LocalTime.of(20,0);

    @Override
    @Transactional
    public void create(ReservationRequest req) {
        LocalDate date = LocalDate.parse(req.getDate());
        LocalTime slot = LocalTime.parse(req.getSlot());
        // 验证时段合法
        if (slot.isBefore(START) || slot.plusMinutes(SLOT_MINUTES).isAfter(END)) {
            throw new IllegalArgumentException("不在可预约时间段内");
        }
        LocalDateTime start = LocalDateTime.of(date, slot);
        LocalDateTime end   = start.plusMinutes(SLOT_MINUTES);

        // 冲突检查
        int c = mapper.countConflict(req.getTableId(), start, end);
        if (c > 0) {
            throw new RuntimeException("该餐桌已被预定，请选择其他时段");
        }

        // 插入
        Reservation r = new Reservation();
        r.setUserId(UserContext.getCurrentId());
        r.setTableId(req.getTableId());
        r.setStartTime(start);
        r.setEndTime(end);
        mapper.insert(r);
    }

    @Override
    public List<Long> getReservedTables(String dateStr, String slotStr) {
        LocalDate date = LocalDate.parse(dateStr);
        LocalDateTime start = LocalDateTime.of(date, LocalTime.parse(slotStr));
        return mapper.selectReservedTableIds(date, start);
    }

    @Override
    public void completeOverdue() {
        List<Long> toComplete = mapper.selectToComplete();
        if (!toComplete.isEmpty()) {
            mapper.batchComplete(toComplete);
        }
    }

    @Override
    public void cancel(ReservationRequest req) {
        log.info("取消预定" + req.toString());
        LocalDate date = LocalDate.parse(req.getDate());
        LocalTime slot = LocalTime.parse(req.getSlot());
        // 验证时段合法
        if (slot.isBefore(START) || slot.plusMinutes(SLOT_MINUTES).isAfter(END)) {
            throw new IllegalArgumentException("不在可预约时间段内");
        }
        LocalDateTime start = LocalDateTime.of(date, slot);
        LocalDateTime end   = start.plusMinutes(SLOT_MINUTES);
        Reservation r = new Reservation();
        r.setUserId(UserContext.getCurrentId());
        r.setTableId(req.getTableId());
        r.setStartTime(start);
        r.setEndTime(end);
        //删除
        mapper.cancel(r);
    }

    @Override
    public List<Reservation> listByUser(Long userId) {
        return mapper.selectByUser(userId);
    }
}
