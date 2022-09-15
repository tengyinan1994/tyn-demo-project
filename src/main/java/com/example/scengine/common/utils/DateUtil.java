package com.example.scengine.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName DateUtil
 * @Description 日期工具类
 * @Author by
 * @Date 13/9/2022 下午5:11
 * @Version 1.0
 */
public final class DateUtil {

    /**
     * @FunctionName stringToDate
     * @Description 把字符串日期转成utilDate
     * @Author by
     * @Date 14/9/2022 下午2:26
     * @Param [dateStr 字符串日期, format 格式]
     * @Return java.util.Date
     **/
    public static Date stringToDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            d = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * @FunctionName dateToString
     * @Description 把utilDate转成字符串日期
     * @Author by
     * @Date 14/9/2022 下午3:29
     * @Param [date utilDate格式日期, format 格式化字符串如yyyy-MM-dd]
     * @Return java.lang.String
     **/
    public static String dateToString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * @FunctionName getCurrentDate
     * @Description 获取当前时间的指定格式
     * @Author by
     * @Date 14/9/2022 下午3:30
     * @Param [format]
     * @Return java.lang.String
     **/
    public static String getCurrentDate(String format) {
        return dateToString(new Date(), format);
    }

    /**
     * @FunctionName timeDifference
     * @Description 获取字符串的两个日期相差多少（单位ms）
     * @Author by
     * @Date 14/9/2022 下午3:38
     * @Param [firstTime 时间1, secTime 时间2, format 格式化字符串]
     * @Return long
     **/
    public static long timeDifference(String firstTime, String secTime, String format) {
        long first = stringToDate(firstTime, format).getTime();
        long second = stringToDate(secTime, format).getTime();
        return Math.abs(second - first);
    }

    /**
     * @FunctionName getDaysOfMonth
     * @Description 获取某年某月的天数
     * @Author by
     * @Date 14/9/2022 下午3:43
     * @Param [year 年, month 月]
     * @Return int
     **/
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * @FunctionName getToday
     * @Description 获取当前的日
     * @Author by
     * @Date 14/9/2022 下午3:45
     * @Return int
     **/
    public static int getToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DATE);
    }

    /**
     * @FunctionName getMonth
     * @Description 获取当前的月
     * @Author by
     * @Date 14/9/2022 下午3:48
     * @Return int
     **/
    public static int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * @FunctionName getYear
     * @Description 获取当前的年
     * @Author by
     * @Date 14/9/2022 下午3:48
     * @Return int
     **/
    public static int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * @FunctionName getToday
     * @Description 返回指定日期的日
     * @Author by
     * @Date 14/9/2022 下午3:56
     * @Param [date]
     * @Return int
     **/
    public static int getToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * @FunctionName getMonth
     * @Description 返回指定日期的月
     * @Author by
     * @Date 14/9/2022 下午3:58
     * @Param [date]
     * @Return int
     **/
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * @FunctionName getYear
     * @Description 返回指定日期的年
     * @Author by
     * @Date 14/9/2022 下午3:59
     * @Param [date]
     * @Return int
     **/
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * @FunctionName dayDiff
     * @Description 计算两个日期差多少天（取绝对值）
     * @Author by
     * @Date 14/9/2022 下午4:02
     * @Param [date1, date2]
     * @Return long
     **/
    public static long dayDiff(Date date1, Date date2) {
        return Math.abs(((date2.getTime() - date1.getTime()) / (1000 * 24 * 60 * 60)));
    }

    /**
     * @FunctionName yearDiff
     * @Description 计算两个日期差多少年（取绝对值）
     * @Author by
     * @Date 14/9/2022 下午4:05
     * @Param [before, after]
     * @Return int
     **/
    public static int yearDiff(String before, String after) {
        Date beforeDay = stringToDate(before, "yyyy-MM-dd");
        Date afterDay = stringToDate(after, "yyyy-MM-dd");
        return Math.abs(getYear(afterDay) - getYear(beforeDay));
    }

    /**
     * @FunctionName getConstellation
     * @Description 根据月日算星座
     * @Author by
     * @Date 14/9/2022 下午4:23
     * @Param [month, day]
     * @Return java.lang.String
     **/
    public static String getConstellation(int month, int day) {
        String s = "魔羯水瓶双鱼白羊金牛双子巨蟹狮子处女天秤天蝎射手魔羯";
        int[] arr = {20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};
        int start = month * 2 - (day < arr[month - 1] ? 2 : 0);
        return s.substring(start, start + 2) + "座";
    }

    /**
     * @FunctionName nextMonth
     * @Description 取得指定日期过months个月后的日期(当months为负数表示指定月之前)
     * @Author by
     * @Date 14/9/2022 下午4:34
     * @Param [date 日期, months 月份]
     * @Return java.util.Date
     **/
    public static Date nextMonth(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * @FunctionName nextDay
     * @Description 取得指定日期过day天后的日期(当day为负数表示指日期之前)
     * @Author by
     * @Date 14/9/2022 下午4:36
     * @Param [date 日期, day 天数]
     * @Return java.util.Date
     **/
    public static Date nextDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.DAY_OF_YEAR, day);
        return cal.getTime();
    }

    /**
     * @FunctionName nextWeek
     * @Description 取得指定日期过week周后的日期(当week为负数表示指定周之前)
     * @Author by
     * @Date 14/9/2022 下午4:40
     * @Param [date 日期, week 周]
     * @Return java.util.Date
     **/
    public static Date nextWeek(Date date, int week) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.add(Calendar.WEEK_OF_MONTH, week);
        return cal.getTime();
    }

    /**
     * @FunctionName nextDay
     * @Description 取得当前日期过day天后的日期(当day为负数表示指日期之前, 带格式化)
     * @Author by
     * @Date 14/9/2022 下午4:39
     * @Param [day 天, format 格式化字符串]
     * @Return java.lang.String
     **/
    public static String nextDay(int day, String format) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, day);
        return dateToString(cal.getTime(), format);
    }

    /**
     * @FunctionName getFirstDayOfMonth
     * @Description 获取本月第一天日期
     * @Author by
     * @Date 14/9/2022 下午4:43
     * @Param [format 格式化字符串]
     * @Return java.lang.String
     **/
    public static String getFirstDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        return dateToString(cal.getTime(), format);
    }

    /**
     * @FunctionName getLastDayOfMonth
     * @Description 获取本月最后一天日期
     * @Author by
     * @Date 14/9/2022 下午4:44
     * @Param [format 格式化字符串]
     * @Return java.lang.String
     **/
    public static String getLastDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return dateToString(cal.getTime(), format);
    }

    /**
     * @FunctionName findDaysStr
     * @Description 计算一段时间内每一天的时间
     * @Author by
     * @Date 13/9/2022 下午5:34
     * @Param [cntDateBeg, cntDateEnd] yyyy-MM-dd格式日期
     * @Return java.util.List<java.lang.String>
     **/
    public static List<String> findDaysStr(String cntDateBeg, String cntDateEnd) {
        List<String> list = new ArrayList<>();
        //拆分成数组
        String[] dateBegs = cntDateBeg.split("-");
        String[] dateEnds = cntDateEnd.split("-");
        //开始时间转换成时间戳
        Calendar start = Calendar.getInstance();
        start.set(Integer.parseInt(dateBegs[0]), Integer.parseInt(dateBegs[1]) - 1, Integer.parseInt(dateBegs[2]));
        long startTIme = start.getTimeInMillis();
        //结束时间转换成时间戳
        Calendar end = Calendar.getInstance();
        end.set(Integer.parseInt(dateEnds[0]), Integer.parseInt(dateEnds[1]) - 1, Integer.parseInt(dateEnds[2]));
        long endTime = end.getTimeInMillis();
        //定义一个一天的时间戳时长
        long oneDay = 1000 * 60 * 60 * 24L;
        long time = startTIme;
        //循环得出
        while (time <= endTime) {
            list.add(new SimpleDateFormat("yyyy-MM-dd").format(new Date(time)));
            time += oneDay;
        }
        return list;
    }

    /**
     * @FunctionName getDateWeek
     * @Description 获取某天是星期几
     * @Author by
     * @Date 13/9/2022 下午5:50
     * @Param [findDaysStr] yyyy-MM-dd格式字符串
     * @Return java.lang.String 星期一为1，星期二为2....日期异常返回0,在isTrue为ture是返回数字，false时返回数字
     **/
    private static String getDateWeek(String findDaysStr, Boolean isTrue) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(findDaysStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                return isTrue ? "1" : "星期一";
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                return isTrue ? "2" : "星期二";
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                return isTrue ? "3" : "星期三";
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                return isTrue ? "4" : "星期四";
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                return isTrue ? "5" : "星期五";
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                return isTrue ? "6" : "星期六";
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                return isTrue ? "7" : "星期天";
            }
        }
        return "0";
    }

    /**
     * @FunctionName getFirstDayAndLastDayOfTheSpecifiedMonth
     * @Description 获取指定年指定月的开始天数和结束天数
     * @Author by
     * @Date 14/9/2022 下午1:40
     * @Param [year 年, month 月]
     * @Return java.util.Map<java.lang.String, java.lang.String>
     **/
    public static Map<String, String> getFirstDayAndLastDayOfTheSpecifiedMonth(int year, int month) {
        // 获取当前分区的日历信息
        Calendar calendar = Calendar.getInstance();
        // 设置年
        calendar.set(Calendar.YEAR, year);
        // 设置月，月份从0开始
        calendar.set(Calendar.MONTH, month - 1);
        // 设置为指定月的第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        // 获取指定月第一天的时间
        Date start = calendar.getTime();
        // 设置日历天数为当前月实际天数的最大值，即指定月份的最后一天
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        // 获取最后一天的时间
        Date end = calendar.getTime();
        // 设置返回信息,返回样式根据需求自行格式化
        Map<String, String> dateMap = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateMap.put("start", sdf.format(start));
        dateMap.put("end", sdf.format(end));
        return dateMap;
    }

//    public static void main(String[] args) {
//        Date date = stringToDate("2022-09-14 14:23:23", "yyyy-MM-dd HH:mm:ss");
//        Date date1 = stringToDate("2022-09-19 14:23:23", "yyyy-MM-dd HH:mm:ss");
//        System.out.println("date = " + date);
//        System.out.println("dateToString(date, \"yyyy-MM-dd HH:mm:ss\") = " + dateToString(date, "yyyy-MM-dd HH:mm:ss"));
//        System.out.println("getCurrentDate(\"yyyy-MM-dd HH:mm:ss\") = " + getCurrentDate("yyyy-MM-dd HH:mm:ss"));
//        System.out.println("timeDifference(\"2022-09-14 14:24:00\", \"2022-09-14 14:23:00\", \"yyyy-MM-dd HH:mm:ss\") = " + timeDifference("2022-09-14 14:24:00", "2022-09-14 14:23:00", "yyyy-MM-dd HH:mm:ss"));
//        System.out.println("getDaysOfMonth(2022, 9) = " + getDaysOfMonth(2022, 9));
//        System.out.println("getToday() = " + getToday());
//        System.out.println("getMonth() = " + getMonth());
//        System.out.println("getYear() = " + getYear());
//        System.out.println("getToday()+ = " + getToday(date));
//        System.out.println("getMonth()+ = " + getMonth(date));
//        System.out.println("getYear()+ = " + getYear(date));
//        System.out.println("dayDiff(date, date1) = " + dayDiff(date, date1));
//        System.out.println("yearDiff(date, date1) = " + yearDiff("2022-09-14 14:23:23", "2025-09-14 14:23:23"));
//        System.out.println("getConstellation(4,8) = " + getConstellation(4, 8));
//        System.out.println("nextMonth(date,6) = " + nextMonth(date, 6));
//        System.out.println("nextDay(date,5) = " + nextDay(date, 5));
//        System.out.println("nextDay(1,\"yyyy-MM-dd\") = " + nextDay(1, "yyyy-MM-dd"));
//        System.out.println("nextWeek(date, 2) = " + nextWeek(date, 2));
//        System.out.println("getFirstDayOfMonth(\"yyyy-MM-dd\") = " + getFirstDayOfMonth("yyyy-MM-dd"));
//        System.out.println("getLastDayOfMonth(\"yyyy-MM-dd\") = " + getLastDayOfMonth("yyyy-MM-dd"));
//        System.out.println("findDaysStr(\"2022-09-01\", \"2022-09-14\") = " + findDaysStr("2022-09-01", "2022-09-14"));
//        System.out.println("getDateWeek(\"2022-09-01\", true) = " + getDateWeek("2022-09-01", true));
//        System.out.println("getDateWeek(\"2022-09-01\", false) = " + getDateWeek("2022-09-01", false));
//        System.out.println("getFirstDayAndLastDayOfTheSpecifiedMonth(2022, 9) = " + getFirstDayAndLastDayOfTheSpecifiedMonth(2022, 9));
//    }

}
