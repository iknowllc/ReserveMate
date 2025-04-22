package com.reservemate.service;

import com.reservemate.pojo.entity.Announcement;
import com.reservemate.pojo.vo.Result;

import java.util.List;

public interface AnnouncementService {
    Result<Void> addAnnouncement(Announcement announcement);
    Result<Void> deleteAnnouncement(Long id);
    Result<Void> updateAnnouncement(Announcement announcement);

    /** 查询所有公告 */
    Result getAllAnnouncements();

    /** 根据 ID 查询公告 */
    Result getAnnouncementById(Long id);
}
