
package com.reservemate.service;

import com.reservemate.pojo.dto.ReservationRequest;
import com.reservemate.pojo.entity.Reservation;

import java.util.List;

public interface ReservationService {
    void create(ReservationRequest req);
    List<Long> getReservedTables(String date, String slot);
    void completeOverdue();
    void cancel(ReservationRequest req);
    List<Reservation> listByUser(Long userId);
}
