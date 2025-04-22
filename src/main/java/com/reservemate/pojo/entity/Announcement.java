package com.reservemate.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    private Long id;       // 公告ID
    private String title;  // 标题
    private String content;// 内容
}
