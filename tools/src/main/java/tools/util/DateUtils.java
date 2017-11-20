package tools.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 日期工具类
 *
 * @author cofey
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils{

    public static final long SECOND = 1000;
    public static final long MINUTE = 60 * SECOND;
    public static final long HOUR = 60 * MINUTE;
    public static final long DAY = 24 * HOUR;
    public static final long WEEK = 7 * DAY;
    /** 日期格式配比 */
    public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

    public static final String yyyyMMddHHmmss = "yyyyMMddHHmmss";

    public static void main(String[] args) throws ParseException {
        double f = 1/100D;
        String objectId = "sdafasdfasdfs3_112";
        objectId = objectId.substring(0, objectId.lastIndexOf("_"));
                System.out.println(objectId);

        Date date = getDateByString("20150506160316", DateUtils.yyyyMMddHHmmss);
        System.out.println(date);
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        Date [] dates = DateUtils.getIntervalDate(now.get(Calendar.YEAR) + "-" + (now.get(Calendar.MONTH) + 1));
        System.out.println(getDateToString(dates[0]));
        System.out.println(getDateToString(dates[1]));
    }

    /**
     * 将日期按指定格式
     *
     * @param date
     * @param format
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 按指定格式将日期字符串转成日期ַ
     *
     * @param str
     * @param format
     */
    public static Date parse(String str, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 按 yyyy-MM-dd 的格式将字符串转换成日期
     *
     * @param arg
     */
    public static Date parse(String arg) {
        return parse(arg, "yyyy-MM-dd");
    }

    /**
     * 获取指定时间后的时间，间隔值单位为分钟
     *
     * @param date
     * @param minute
     */
    public static Date afterMinute(Date date, long minute) {
        long time = date.getTime();
        time += minute * MINUTE;
        return new Date(time);
    }

    /**
     * 获取指定时间前的时间，间隔值单位为分钟
     *
     * @param date
     * @param minute
     */
    public static Date beforeMinute(Date date, long minute) {
        long time = date.getTime();
        time -= minute * MINUTE;
        return new Date(time);
    }

    /**
     * 获取当前时间
     */
    public static String curTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = format.format(date);
        return str;
    }

    /**
     * 获取当前日期
     */
    public static String curDate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String str = format.format(date);
        return str;
    }

    /**
     * 获取当前日
     */
    public static int getDay() {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return Integer.parseInt((sdf.format(now.getTime())));
    }

    /**
     * 获取当前日期的前一天
     */
    public static int getBeforeDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);    //得到前一天
        Date date = calendar.getTime();
        String DATE_FORMAT = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return Integer.parseInt((sdf.format(date)));
    }

    /**
     * 获取当前日期的前6天
     * 返回数字格式
     */
    public static int getBefore6Day() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -6);
        Date date = calendar.getTime();
        String DATE_FORMAT = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return Integer.parseInt((sdf.format(date)));
    }
    /**
     * 获取当前日期的前6天
     * 返回字符串格式
     */
    public static String getBefore6DayStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -6);
        Date date = calendar.getTime();
        String DATE_FORMAT = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }

    /**
     * 获取当前日期的前6个月
     */
    public static int getBefore6Month() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -6);
        Date date = calendar.getTime();
        String DATE_FORMAT = "yyyyMM";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return Integer.parseInt((sdf.format(date)));
    }

    /**
     * 获取当前日期的前6年
     */
    public static int getBefore6Year() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -6);
        Date date = calendar.getTime();
        String DATE_FORMAT = "yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return Integer.parseInt((sdf.format(date)));
    }

    /**
     * 获取当前年份
     */
    public static int getYear() {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return Integer.parseInt((sdf.format(now.getTime())));
    }

    /**
     * 获取当前月份
     */
    public static int getMonth() {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        String DATE_FORMAT = "yyyyMM";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return Integer.parseInt((sdf.format(now.getTime())));
    }

    /**
     * 比较两个时间的大小
     *
     * @param fistTime
     * @param secondTime
     * @return
     */
    public static boolean compareTimeSize(String fistTime, String secondTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = format.parse(fistTime);
            Date d2 = format.parse(secondTime);
            if (d1.after(d2)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 传入时间字符串返回时间
     * @param Time
     * @return
     */
    public static Date getDateByString(String Time){
        if(StringUtils.isNotEmpty(Time)){
            try {
                Date d1=org.apache.commons.lang3.time.DateUtils.parseDate(Time,DATE_PATTERNS);
                return d1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 传入时间字符串返回时间
     * @param Time
     * @return
     */
    public static Date getDateByString(String Time,String formatStr){
        if(StringUtils.isNotEmpty(Time)){
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            try {
                Date d1 = format.parse(Time);
                return d1;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 传入时间字符串返回时间
     * @param time
     * @return yyyy-MM-dd
     */
    public static String getDateToString(Date time){
        if(time != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return format.format(time);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 返回自定义日期格式
     * @param time
     * @param fomat
     * @return
     */
    public static String getDateToString(Date time,String fomat){
        if(time != null){
            SimpleDateFormat format = new SimpleDateFormat(fomat);
            try {
                return format.format(time);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 传入时间返回时间字符串
     * @param time
     * @return
     */
    public static String getStringByDate(Date time){
        if(time != null){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                return format.format(time);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 计算两个日期之间的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getDays(Date date1, Date date2) {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;
        gc1 = new GregorianCalendar();
        gc2 = new GregorianCalendar();
        if (date1.after(date2)) {
            gc1.setTime(date2);
            gc2.setTime(date1);
        } else {
            gc1.setTime(date1);
            gc2.setTime(date2);
        }
        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);

        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.HOUR_OF_DAY);

        while (gc1.before(gc2)) {
            gc1.add(Calendar.DATE, 1);
            elapsed++;
        }
        return elapsed;
    }

    /**
     * 计算2个日期之间的月数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getMonths(Date date1, Date date2) {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;
        gc1 = new GregorianCalendar();
        gc2 = new GregorianCalendar();
        if (date1.after(date2)) {
            gc1.setTime(date2);
            gc2.setTime(date1);
        } else {
            gc1.setTime(date1);
            gc2.setTime(date2);
        }

        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);
        gc1.clear(Calendar.DATE);

        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.HOUR_OF_DAY);
        gc2.clear(Calendar.DATE);

        while (gc1.before(gc2)) {
            gc1.add(Calendar.MONTH, 1);
            elapsed++;
        }
        return elapsed;
    }

    /**
     * 计算2个日期之间的年数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getYear(Date date1, Date date2) {
        int elapsed = 0;
        GregorianCalendar gc1, gc2;
        gc1 = new GregorianCalendar();
        gc2 = new GregorianCalendar();
        if (date1.after(date2)) {
            gc1.setTime(date2);
            gc2.setTime(date1);
        } else {
            gc1.setTime(date1);
            gc2.setTime(date2);
        }

        gc1.clear(Calendar.MILLISECOND);
        gc1.clear(Calendar.SECOND);
        gc1.clear(Calendar.MINUTE);
        gc1.clear(Calendar.HOUR_OF_DAY);
        gc1.clear(Calendar.DATE);

        gc2.clear(Calendar.MILLISECOND);
        gc2.clear(Calendar.SECOND);
        gc2.clear(Calendar.MINUTE);
        gc2.clear(Calendar.HOUR_OF_DAY);
        gc2.clear(Calendar.DATE);

        while (gc1.before(gc2)) {
            gc1.add(Calendar.YEAR, 1);
            elapsed++;
        }
        return elapsed;
    }

    /**
     * 获取月份的最后一天
     *
     * @param dateStr 格式为yyyy-MM-dd
     * @return
     */
    public static int getLastDayOfMonth(String dateStr) {
        Date date = parse(dateStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return lastDay;
    }

    /**
     * 获取上个月的月份
     *
     * @return
     */
    public static String getBeforeMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(d);
    }

    /**
     * 获取本个月的月份
     *
     * @return
     */
    public static String getThisMonth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        Date d = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        return sdf.format(d);
    }

    /**
     * 获取本周的第一天
     *
     * @return
     */
    public static int getNowWeekFirstDay() {
        int mondayPlus;
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            mondayPlus = 0;
        } else {
            mondayPlus = 1 - dayOfWeek;
        }
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date date = currentDate.getTime();
        String DATE_FORMAT = "yyyyMMdd";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setTimeZone(TimeZone.getDefault());
        return Integer.parseInt((sdf.format(date)));
    }

    /**
     * 获取指定日期当前周内的指定周几的日期
     * @param date 指定日期
     * @param dayIndexStartWithMonday 指定第几天 一周从周一开始,范围1-7
     * @return
     */
    public static Date getWeekDayIndexDate(Date date,int dayIndexStartWithMonday) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;//从周日1-7
        dayOfWeek = (dayOfWeek == 0 ? 7 : dayOfWeek);//周日为第7天
        if(dayOfWeek == dayIndexStartWithMonday){
            return date;
        }
        return addDays(date, (dayIndexStartWithMonday - dayOfWeek));
    }

    /**
     * 提供一个日期字符串和日期格式，获取其中一个域的值
     *
     * @param pattern 如：yyyy-MM-dd hh:mm:ss
     * @param time    如：2014-10-25 20:22:38
     * @param field   如：Calendar.YEAR则返回2014，Calendar.MONTH则返回10
     * @return
     */
    public static Integer get(String pattern, String time, int field) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(time);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            if (field == Calendar.MONTH)
                return c.get(field) + 1;
            return c.get(field);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取一个月的日期区间
     *
     * @param month yyyy-MM格式
     * @return
     * @throws ParseException
     */
    public static String[] getInterval(String month) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date beginTime = sdf.parse(month);
        Calendar c = Calendar.getInstance();
        c.setTime(beginTime);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        c.add(Calendar.DATE, -1);
        Date endTime = c.getTime();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        return new String[]{sdf.format(beginTime), sdf.format(endTime)};
    }

    /**
     * 获取一个月的日期区间
     *
     * @param month yyyy-MM格式
     * @return
     * @throws ParseException
     */
    public static Date[] getIntervalDate(String month) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date beginTime = sdf.parse(month);
        Calendar c = Calendar.getInstance();
        c.setTime(beginTime);
        c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
        c.add(Calendar.DATE, -1);
        Date endTime = c.getTime();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
        return new Date[]{beginTime, endTime};
    }

    /**
     * 获取一年的时间区间
     *
     * @param year
     * @return
     * @throws ParseException
     */
    public static Date[] getInterval(int year) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.set(year, 0, 1, 0, 0, 0);
        Date beginTime = c.getTime();
        c.set(year, 11, 31, 23, 59, 59);
        Date endTime = c.getTime();
        return new Date[]{beginTime, endTime};
    }


    /**
     * 加减天数
     *
     * @param num
     * @param Date
     * @return
     */
    public static Date addDay(int num, Date Date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date);
        calendar.add(Calendar.DATE, num);// 把日期往后增加 num 天.整数往后推,负数往前移动
        return calendar.getTime(); // 这个时间就是日期往后推一天的结果
    }

    /**
     * 加减月数数
     *
     * @param num
     * @param Date
     * @return
     */
    public static String addMonth(int num, Date Date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date);
        calendar.add(Calendar.MONTH, num);// 把日期往后增加 num 月.整数往后推,负数往前移动
        return sdf.format(calendar.getTime()); // 这个时间就是日期往后推一月的结果
    }

    /**
     * 加减年数
     *
     * @param num
     * @param Date
     * @return
     */
    public static String addYear(int num, Date Date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date);
        calendar.add(Calendar.YEAR, num);// 把日期往后增加 num 年.整数往后推,负数往前移动
        return sdf.format(calendar.getTime()); // 这个时间就是日期往后推一年的结果
    }

    /**
     * 把yyyy-MM-dd格式的字符串日期解析为带23:59:59时间的Date日期对象
     *
     * @param endTime
     * @return
     */
    public static Date parseEndTime(String endTime) {
        Date date = parse(endTime);

        return getEndTime(date);
    }

    /**
     * 把yyyy-MM-dd格式的字符串日期解析为带00:00:00时间的Date日期对象
     *
     * @param beginTime
     * @return
     */
    public static Date parseBeginTime(String beginTime) {
        Date date = parse(beginTime);

        return getBeginTime(date);
    }

    /**
     * 获取开始时间
     *
     * @param date
     * @return
     */
    public static Date getBeginTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);

        calendar.set(Calendar.YEAR, dateCalendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, dateCalendar.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, dateCalendar.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();
    }

    /**
     * 获取结束时间
     *
     * @param date
     * @return
     */
    public static Date getEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        Calendar dateCalendar = Calendar.getInstance();
        dateCalendar.setTime(date);

        calendar.set(Calendar.YEAR, dateCalendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, dateCalendar.get(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, dateCalendar.get(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    /**
     *小时转换为分钟
     *
     * @param countDownTime
     * @return
     */
    public static int convertMinutes(String countDownTime) {
        BigDecimal bd = new BigDecimal(countDownTime);
        bd = bd.multiply(new BigDecimal("60")).setScale(0, BigDecimal.ROUND_HALF_UP);

        return bd.intValue();
    }

    /**
     * 分钟转换为小时
     *
     * @param countDownTime
     * @return
     */
    public static double convertHours(Integer countDownTime) {
        BigDecimal bd = new BigDecimal(String.valueOf(countDownTime));
        bd = bd.divide(new BigDecimal("60"), 1, BigDecimal.ROUND_HALF_EVEN);
        return bd.doubleValue();
    }

    /**
     * 添加分钟
     *
     * @param date
     * @param minutes
     * @return
     */
    public static Date addMinutes(Date date, int minutes) {
        if (date == null) {
            return null;
        }
        // 初始化日历对象
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);

        // 返回操作结果
        return cal.getTime();
    }

}