package com.reservemate.service.impl;

import com.reservemate.mapper.AnnouncementMapper;
import com.reservemate.pojo.entity.Announcement;
import com.reservemate.pojo.vo.Result;
import com.reservemate.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public Result<Void> addAnnouncement(Announcement announcement) {
        announcementMapper.insert(announcement);
        return Result.success();
    }

    @Override
    public Result<Void> deleteAnnouncement(Long id) {
        announcementMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result<Void> updateAnnouncement(Announcement announcement) {
        announcementMapper.update(announcement);
        return Result.success();
    }

    @Override
    public Result getAllAnnouncements() {
        return Result.success(announcementMapper.selectAll());
    }

    @Override
    public Result getAnnouncementById(Long id) {
        return Result.success(announcementMapper.selectById(id));
    }
}
