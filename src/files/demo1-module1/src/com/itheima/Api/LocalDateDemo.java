package com.itheima.Api;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

public class LocalDateDemo {
    public static void main(String[] args) {
        // 获取当前时间的日历对象
        LocalDate l1 = LocalDate.now();         // 2024-08-21
        System.out.println(l1);

        // 指定日期
        LocalDate l2 = LocalDate.of(2024,11,11);
        System.out.println(l2);

        System.out.println(l2.getYear());
        System.out.println(l2.getMonthValue());
        System.out.println(l2.getDayOfMonth());
        System.out.println(l2.getDayOfWeek());          // MONDAY

        Period p1 = Period.between(l1,l2);  // 返回一个对象
        System.out.println(p1);
        System.out.println(p1.getYears());
        System.out.println(p1.getMonths());
        System.out.println(p1.getDays());

        Duration d1 = Duration.between(l1, l2);
        System.out.println(d1.toDays());
        System.out.println(d1.toHours());
        System.out.println(d1.toMinutes());
    }
}
