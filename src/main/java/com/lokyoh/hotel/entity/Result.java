package com.lokyoh.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public static <E> Result<E> success(E data) {
        return new Result<>(200, "操作成功", data);
    }

    public static <E> Result<E> success() {
        return new Result<>(200, "操作成功", null);
    }

    public static <E> Result<E> error(Integer code, String message) {
        return new Result<>(400, message, null);
    }
}
