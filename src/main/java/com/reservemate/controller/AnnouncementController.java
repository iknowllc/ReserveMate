package com.reservemate.controller;

import com.reservemate.pojo.entity.Announcement;
import com.reservemate.pojo.vo.Result;
import com.reservemate.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    /** 增加公告 */
    @PostMapping("/add")
    public Result<Void> add(@RequestBody Announcement announcement) {
        return announcementService.addAnnouncement(announcement);

    }

    /** 删除公告 */
    @DeleteMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        return announcementService.deleteAnnouncement(id);

    }

    /** 修改公告 */
    @PutMapping("/update")
    public Result<Void> update(@RequestBody Announcement announcement) {
        return announcementService.updateAnnouncement(announcement);

    }

    /** 查询所有公告 */
    @GetMapping("/list")
    public Result<List<Announcement>> list() {
        return (Result<List<Announcement>>) announcementService.getAllAnnouncements();
    }

    /**
     * 根据 ID 查询单条公告
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id) {
       return announcementService.getAnnouncementById(id);
    }
}
