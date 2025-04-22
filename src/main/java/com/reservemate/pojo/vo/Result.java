package com.reservemate.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private boolean success;   // 操作是否成功
    private String message;    // 提示信息
    private T data;            // 返回的数据，可以是任意类型
    private int code;          // 状态码，200 为成功，其他值为失败等

    // 自定义成功返回方法
    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .success(true)
                .message("操作成功")
                .data(data)
                .code(200)
                .build();
    }

    // 自定义失败返回方法
    public static <T> Result<T> failure(String message) {
        return Result.<T>builder()
                .success(false)
                .message(message)
                .data(null)
                .code(400)
                .build();
    }
    public static <T> Result<T> failure() {
        return Result.<T>builder()
                .success(false)
                .data(null)
                .code(400)
                .build();
    }
    public static <T> Result<T> success() {
        return Result.<T>builder()
                .success(true)
                .message("操作成功")
                .code(200)
                .build();
    }
}

