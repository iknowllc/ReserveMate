
package com.reservemate.pojo.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Reservation {
    private Long reservationId;
    private Long userId;
    private Long tableId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
