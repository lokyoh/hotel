package com.lokyoh.hotel.utils;

public class PageCheckerUtil {
    public static void checkPage(Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        if (pageNum < 1) throw new RuntimeException("pageNum错误");
        if (pageSize > 30 || pageSize < 1) throw new RuntimeException("pageSize错误");
    }
}
