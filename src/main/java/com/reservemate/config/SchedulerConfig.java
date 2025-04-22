package com.reservemate.config;

import com.reservemate.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    private ReservationService reservationService;

    /** 每分钟跑一次，标记已过 end_time 的订单为 completed */
    @Scheduled(cron = "0 */1 * * * ?")
    public void completeOverdueOrders() {
        reservationService.completeOverdue();
    }
}
