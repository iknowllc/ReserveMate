package com.reservemate.pojo.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableInfo {

    private Long tableId;             // 餐桌ID
    private String tableName;
    private Integer capacity;
    private String location;
    private String status;
    private String photoUrl;      // ← 新增字段

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

