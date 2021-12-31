package com.tv.demo001.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.apache.commons.lang.time.DateFormatUtils;

/**
 * @author hubo88
 * @type
 * @description
 * @dependency
 * @emitter
 */
public class DateFormatUtilsTest {

    /**
     * 5min 用毫秒 表示
     */
    private static final long FIVE_MINS = 300000L;

    public static void main(String[] args) {
//        System.out.println(Long.valueOf(DateFormatUtils.format(new Date(), "yyyyMMddHHmm")));
//        System.out.println(DateTimeFormatter.ofPattern("yyyyMMddHH").format(
//            LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault())));
//
////        long lastTime = LocalDateTime.parse(String.valueOf(this.refreshTime), DateTimeFormatter.ofPattern("yyyyMMddHH"))
////            .until(LocalDateTime.now(), ChronoUnit.HOURS);
//
//        System.out.println(1000 == Integer.valueOf(1000));

        System.out.println(currentFiveMinsTime());

    }

    private static long currentFiveMinsTime(){
        return System.currentTimeMillis()-System.currentTimeMillis() % FIVE_MINS;
    }
}
