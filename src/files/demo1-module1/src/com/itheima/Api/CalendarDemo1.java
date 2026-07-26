package com.itheima.Api;

import java.util.Calendar;

public class CalendarDemo1 {
    public static void main(String[] args) {
        // 日历对象
        // Calendar是一个抽象类，不能new，只能通过一个静态方法获取子类对象
        Calendar c1 = Calendar.getInstance();       // 当前地区时间，很长的一个字符串，包含所有
        System.out.println(c1);

        c1.set(Calendar.YEAR, 2022);                // 修改年份

        c1.add(Calendar.YEAR, 1);           // 年份加1，负数减

        int year = c1.get(Calendar.YEAR);
        int month = c1.get(Calendar.MONTH) + 1;
        int day = c1.get(Calendar.DAY_OF_MONTH);
        int weekDay = c1.get(Calendar.DAY_OF_WEEK);         // 当前周的第几天
        System.out.println(year + "-" + month + "-" + day + "-" + weekDay);             // 2024 8 21
    }
}
