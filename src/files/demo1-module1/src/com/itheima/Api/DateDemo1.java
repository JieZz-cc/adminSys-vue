package com.itheima.Api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateDemo1 {
    public static void main(String[] args) throws ParseException {
        Date d1 = new Date();       // 当前时间
        System.out.println(d1);
        Date d2 = new Date(0L);     // 时间起点过了0毫秒， 中国东8区，加了8小时 Thu Jan 01 08:00:00 CST 1970
        d2.setTime(1000L);          // 过了1000ms，修改时间  Thu Jan 01 08:00:01 CST 1970
        System.out.println(d2.getTime()); //获取时间的毫秒数

        System.out.println(d1);
        System.out.println(d2);


        SimpleDateFormat s1 = new SimpleDateFormat();   // 默认格式 1970/1/1 上午8:00
        String str1 = s1.format(d2);
        System.out.println(str1);

        SimpleDateFormat s2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");     //1970年01月01日 08:00:01
        System.out.println(s2.format(d2));

        SimpleDateFormat s3 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss EE");
        System.out.println(s3.format(d2));              // 1970年01月01日 08:00:01 周四

        SimpleDateFormat s4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(s4.format(d2));                                      // 1970-01-01 08:00:01
        System.out.println(s4.parse("2024-08-21 15:24:00"));            // Wed Aug 21 15:24:00 CST 2024

        System.out.println("---------------------------------------");

        // instant   当前时间的instant对象
        Instant now = Instant.now();
        System.out.println(now);            // 世界标准时间，中国要加8 2024-08-21T08:09:07.967809100Z

        System.out.println(Instant.ofEpochMilli(0L));          //1970-01-01T00:00:00Z

        System.out.println(Instant.ofEpochSecond(1L));          // 1970-01-01T00:00:01Z

        ZonedDateTime zoneTime = Instant.now().atZone(ZoneId.of("Asia/shanghai"));      // 指定区域时间

        // instent1.ifBefore(instent2)  判断时间先后，返回boolean
        // instent1.ifAfter(instent2)  判断时间先后， 返回boolean

        // instent1.minusSeconds(1)        减去1秒




    }
}
