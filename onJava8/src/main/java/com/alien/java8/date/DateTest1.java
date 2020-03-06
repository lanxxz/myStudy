package com.alien.java8.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * program: myStudy
 * description: Java 8 新的时间工具测试
 *
 * @author: alien
 * @since: 2020/03/06 18:01
 */
public class DateTest1 {

    public static void main(String[] args) {
        // 获取今天日期
        LocalDate localDate = LocalDate.now();
        System.out.println("今天日期:" + localDate);
        // 指定日期，进行相关操作
        LocalDate firstDay = localDate.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("本月第一天：" + firstDay);
        LocalDate lastDay = localDate.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("本月最后一天：" + lastDay);
        LocalDate tomorrow = localDate.plusDays(1);
        System.out.println("明天：" + tomorrow);
        boolean leapYear = tomorrow.isLeapYear();
        System.out.println("是否闰年：" + leapYear);
        // 生日检查或者账单日检查
        LocalDate birthday = LocalDate.of(1990, 10, 12);
        MonthDay birthdayMd = MonthDay.of(birthday.getMonth(), birthday.getDayOfMonth());
        MonthDay today = MonthDay.from(LocalDate.of(2016, 10, 12));
        System.out.println(birthdayMd.equals(today));

        // 获取当前的时间
        LocalTime now = LocalTime.now();
        System.out.println(now);
        // 不显示 毫秒
        LocalTime nowTime = LocalTime.now().withNano(0);
        System.out.println(nowTime);
        // 指定时间
        LocalTime time = LocalTime.of(14, 10, 21);
        LocalTime time2 = LocalTime.parse("12:00:02");
        System.out.println(time + " ---- " + time2);
        // 当前时间加两小时
        LocalTime nowTimePlus2Hour = nowTime.plusHours(2);
        LocalTime nowTimePlus2Hour2 = nowTime.plus(2, ChronoUnit.HOURS);
        System.out.println(nowTimePlus2Hour + " ---- " + nowTimePlus2Hour2);

        // 日期前后比较
        LocalDate sprcifyDate = LocalDate.of(2020, 3, 9);
        System.out.println(localDate.isAfter(sprcifyDate));

        // 处理不同时区
        System.out.println("===========");
        // 查看当前时区
        ZoneId defaultZone = ZoneId.systemDefault();
        System.out.println(defaultZone);

        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime americaDateTime = LocalDateTime.now(america);
        LocalDateTime shanghaiTime = LocalDateTime.now();
        System.out.println(shanghaiTime);
        System.out.println(americaDateTime);
        // 带有时区的时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now(america);
        System.out.println(zonedDateTime);

        // 比较两个时间之间时间差
        System.out.println("========");
        LocalDate specifyDate = LocalDate.of(2015, 10, 2);

        Period period = Period.between(specifyDate, localDate);
        System.out.println(period.getDays());
        System.out.println(period.getMonths());
        System.out.println(specifyDate.until(localDate, ChronoUnit.DAYS));

        // 日期时间格式解析、格式化
        String specifyStr = "20151011";
        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
        LocalDate formatted = LocalDate.parse(specifyStr, formatter);
        System.out.println(formatted);

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("YYYY MM dd");
        System.out.println(formatter1.format(LocalDate.now()));

        // java 8 实践类与 Date 类 相互转换
        // Date 与 Instant 相互转换
        Instant instant = Instant.now();
        Date date = Date.from(instant);
        Instant instant1 = date.toInstant();

        Date date1 = new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date1.toInstant(), ZoneId.systemDefault());

        LocalDateTime now1 = LocalDateTime.now();
        Instant instant2 = now1.atZone(ZoneId.systemDefault()).toInstant();
        Date from = Date.from(instant2);

        LocalDate now2 = LocalDate.now();
        Instant instant3 = now2.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        Date from1 = Date.from(instant);

    }

}
