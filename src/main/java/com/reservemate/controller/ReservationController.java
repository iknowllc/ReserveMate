
package com.reservemate.controller;

import com.reservemate.common.context.UserContext;
import com.reservemate.pojo.dto.ReservationRequest;
import com.reservemate.pojo.entity.Reservation;
import com.reservemate.pojo.vo.Result;
import com.reservemate.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    private ReservationService service;

    /** 1. 创建预定 */
    @PostMapping
    public Result<Void> create(@RequestBody ReservationRequest req) {
        service.create(req);
        return Result.success();
    }

    /** 2. 查询某时段已被预定的餐桌ID */
    @GetMapping("/availability")
    public Result<List<Long>> availability(
            @RequestParam String date,
            @RequestParam String slot
    ) {
        List<Long> reserved = service.getReservedTables(date, slot);
        log.info(reserved.toString());
        return Result.success(reserved);
    }

    /** 3. （后台定时完成，无需手动接口） */

    /** 4. 取消预定 */
    @DeleteMapping()
    public Result<Void> cancel(@RequestBody ReservationRequest reservationRequest) {
        log.info("取消预定");
        service.cancel(reservationRequest);
        return Result.success();
    }

    /** 查看我的预定 */
    @GetMapping("/user")
    public Result<List<Reservation>> listByUser() {
        List<Reservation> list = service.listByUser(UserContext.getCurrentId());
        return Result.success(list);
    }
}
