
package com.reservemate.pojo.dto;

import lombok.Data;

/**
 * 前端传入：userId, tableId, date (yyyy-MM-dd), slot (HH:mm)
 */
@Data
public class ReservationRequest {
    private Long tableId;
    private String date;
    private String slot;
}
