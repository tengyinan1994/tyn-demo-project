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
public class DateUtil {

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
     * @Return java.lang.Integer 星期一为1，星期二为2....日期异常返回0
     **/
    private static Integer getDateWeek(String findDaysStr) {
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
                return 1;
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
                return 2;
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
                return 3;
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                return 4;
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                return 5;
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                return 6;
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                return 7;
            }
        }
        return 0;
    }
}
