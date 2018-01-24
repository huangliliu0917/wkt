package com.zmj.wkt.utils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @author : zmj
 * @description : 通用日期工具类
 * ---------------------------------
 */
public class DateUtil implements Serializable, Cloneable {
    public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static int SUNDAY = 1;
    public static int MONDAY = 2;
    public static int TUESDAY = 3;
    public static int WEDNESDAY = 4;
    public static int THURSDAY = 5;
    public static int FRIDAY = 6;
    public static int SATURDAY = 7;
    private int a;
    private int b;
    private int c;

    public static boolean checkDate(String var0) {
        var0 = var0.replaceAll("-", "").replaceAll("/", "").replaceAll("\\.", "").replaceAll("年", "").replaceAll("月", "").replaceAll("日", "");
        SimpleDateFormat var1;
        (var1 = new SimpleDateFormat("yyyyMMdd")).setLenient(false);

        try {
            var1.parse(var0);
            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public DateUtil() {
        GregorianCalendar var1 = new GregorianCalendar();
        this.c = var1.get(1);
        this.b = var1.get(2) + 1;
        this.a = var1.get(5);
    }

    public DateUtil(int var1, int var2, int var3) {
        this.c = var1;
        this.b = var2;
        this.a = var3;
        DateUtil var4;
        (var4 = new DateUtil()).a(this.a());
        if (var4.a != this.a || var4.b != this.b || var4.c != this.c) {
            throw new IllegalArgumentException();
        }
    }

    public void setDate(int var1, int var2, int var3) {
        this.c = var1;
        this.b = var2;
        this.a = var3;
    }

    public void setDate(String var1, String var2, String var3) {
        try {
            this.setDate(Integer.parseInt(var1), Integer.parseInt(var2), Integer.parseInt(var3));
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public static Date parse(String var0, String var1) throws Exception {
        SimpleDateFormat var3 = new SimpleDateFormat(var1);

        try {
            return var3.parse(var0);
        } catch (Exception var2) {
            throw new Exception("日期格式错误:" + var0);
        }
    }

    public static java.sql.Date parseSqlDate(String var0, String var1) throws Exception {
        SimpleDateFormat var3 = new SimpleDateFormat(var1);

        try {
            return new java.sql.Date(var3.parse(var0).getTime());
        } catch (Exception var2) {
            throw new Exception("日期格式错误:" + var0);
        }
    }

    public static String getDayList(String var0) {
        String var1 = "";
        String var2 = null;
        var0 = var0 == null ? "" : var0;

        for(int var3 = 1; var3 < 32; ++var3) {
            var2 = var3 < 10 ? "0" + var3 : "" + var3;
            if (var0.equals(var2)) {
                var1 = var1 + "<option value=\"" + var2 + "\" selected>" + var2 + "</option>\n";
            } else {
                var1 = var1 + "<option value=\"" + var2 + "\">" + var2 + "</option>\n";
            }
        }

        return var1;
    }

    public static String getMonthList(String var0) {
        String var1 = "";
        String var2 = null;
        var0 = var0 == null ? "" : var0;

        for(int var3 = 1; var3 < 13; ++var3) {
            var2 = var3 < 10 ? "0" + var3 : "" + var3;
            if (var0.equals(var2)) {
                var1 = var1 + "<option value=\"" + var2 + "\" selected>" + var2 + "</option>\n";
            } else {
                var1 = var1 + "<option value=\"" + var2 + "\">" + var2 + "</option>\n";
            }
        }

        return var1;
    }

    public static String getYearList(String var0, int var1, int var2) {
        String var3 = "";
        var0 = var0 == null ? "" : var0;
        String var4 = null;

        for(var1 = var1; var1 <= var2; ++var1) {
            var4 = String.valueOf(var1);
            if (var0.equals(var4)) {
                var3 = var3 + "<option value=\"" + var4 + "\" selected>" + var4 + "</option>\n";
            } else {
                var3 = var3 + "<option value=\"" + var4 + "\">" + var4 + "</option>\n";
            }
        }

        return var3;
    }

    public void advance(int var1) {
        this.a(this.a() + var1);
    }

    public int getDay() {
        return this.a;
    }

    public int getMonth() {
        return this.b;
    }

    public int getYear() {
        return this.c;
    }

    public int weekday() {
        return (this.a() + 1) % 7 + 1;
    }

    public static String formatDate(Date var0, String var1) {
        return (new SimpleDateFormat(var1)).format(var0);
    }

    public static String formatDate(String var0, String var1, String var2) {
        if (var0 == null) {
            return null;
        } else {
            try {
                return formatDate((new SimpleDateFormat(var1)).parse(var0), var2);
            } catch (Exception var3) {
                return null;
            }
        }
    }

    public static String formatDate(Date var0) {
        return dateFormat.format(var0);
    }

    public static Date formatDateTime(String var0, String var1) {
        if (var0 == null) {
            return null;
        } else {
            try {
                return (new SimpleDateFormat(var1)).parse(var0);
            } catch (Exception var2) {
                var2.printStackTrace();
                return null;
            }
        }
    }

    public int daysBetween(DateUtil var1) {
        int var2;
        if ((var2 = this.a() - var1.a()) < 0) {
            var2 = -var2;
        }

        return var2;
    }

    @Override
    public final String toString() {
        return "Day[" + this.c + "," + this.b + "," + this.a + "]";
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException var1) {
            return null;
        }
    }

    @Override
    public final boolean equals(Object var1) {
        if (!this.getClass().equals(var1.getClass())) {
            return false;
        } else {
            DateUtil var2 = (DateUtil)var1;
            return this.a == var2.a && this.b == var2.b && this.c == var2.c;
        }
    }

    private int a() {
        int var1 = this.c;
        if (this.c < 0) {
            ++var1;
        }

        int var2 = this.b;
        if (this.b > 2) {
            ++var2;
        } else {
            --var1;
            var2 += 13;
        }

        var2 = (int)(Math.floor(365.25D * (double)var1) + Math.floor(30.6001D * (double)var2) + (double)this.a + 1720995.0D);
        if (this.a + 31 * (this.b + 12 * this.c) >= 588829) {
            var1 = (int)(0.01D * (double)var1);
            var2 += 2 - var1 + (int)(0.25D * (double)var1);
        }

        return var2;
    }

    private void a(int var1) {
        int var2 = var1;
        if (var1 >= 2299161) {
            var2 = (int)(((double)((float)(var1 - 1867216)) - 0.25D) / 36524.25D);
            var2 = var1 + (var2 + 1 - (int)(0.25D * (double)var2));
        }

        var2 += 1524;
        var1 = (int)(6680.0D + ((double)((float)(var2 - 2439870)) - 122.1D) / 365.25D);
        int var3 = (int)((double)(var1 * 365) + 0.25D * (double)var1);
        int var4 = (int)((double)(var2 - var3) / 30.6001D);
        this.a = var2 - var3 - (int)(30.6001D * (double)var4);
        this.b = var4 - 1;
        if (this.b > 12) {
            this.b -= 12;
        }

        this.c = var1 - 4715;
        if (this.b > 2) {
            --this.c;
        }

        if (this.c <= 0) {
            --this.c;
        }

    }

    public static String formatDate(String var0, String var1) {
        String var2 = null;
        if (var0 == null) {
            return "";
        } else {
            for(int var3 = 0; var3 < var0.length(); ++var3) {
                if (!Character.isDigit(var0.charAt(var3))) {
                    return var0;
                }
            }

            if (var1.indexOf("yyyy") >= 0) {
                var2 = var0.substring(0, 4);
                var1 = replace(var1, "yyyy", var2);
            }

            if (var1.indexOf("yy") >= 0) {
                var2 = var0.substring(2, 4);
                var1 = replace(var1, "yy", var2);
            }

            if (var1.indexOf("MM") >= 0) {
                var2 = var0.substring(4, 6);
                var1 = replace(var1, "MM", var2);
            }

            if (var1.indexOf("dd") >= 0) {
                var2 = var0.substring(6, 8);
                var1 = replace(var1, "dd", var2);
            }

            if (var1.indexOf("HH") >= 0) {
                var2 = var0.substring(8, 10);
                var1 = replace(var1, "HH", var2);
            }

            if (var1.indexOf("hh") >= 0) {
                if (Integer.parseInt(var2 = var0.substring(8, 10)) > 12) {
                    var2 = Integer.toString(Integer.parseInt(var2) - 12);
                }

                var1 = replace(var1, "hh", var2);
            }

            if (var1.indexOf("mm") >= 0) {
                var2 = var0.substring(10, 12);
                var1 = replace(var1, "mm", var2);
            }

            if (var1.indexOf("ss") >= 0) {
                var2 = var0.substring(12);
                var1 = replace(var1, "ss", var2);
            }

            return var1;
        }
    }

    public static String replace(String var0, String var1, String var2) {
        if (var0 == null) {
            return "";
        } else {
            int var3 = 0;
            int var4;
            if ((var4 = var1.length()) <= 0) {
                return var0;
            } else {
                for(int var5 = var2.length(); (var3 = var0.indexOf(var1, var3)) != -1; var3 += var5) {
                    var0 = var0.substring(0, var3) + var2 + var0.substring(var3 + var4);
                }

                return var0;
            }
        }
    }

    public static String getNowDate() {
        return (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
    }

    public static String getNowTime() {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
    }

    public static String getNowDateNormal() {
        return (new SimpleDateFormat("yyyyMMdd")).format(new Date());
    }

    public static String getNowTimeNormal() {
        return (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
    }

    public static String getNowTimestampStr() {
        return (new Timestamp((new Date()).getTime())).toString();
    }

    public static Timestamp getNowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String processDate(Date var0) {
        if (var0 == null) {
            return "";
        } else {
            try {
                return (new SimpleDateFormat("yyyy-MM-dd")).format(var0);
            } catch (Exception var2) {
                return var0.toString();
            }
        }
    }

    public static String processTime(Date var0) {
        if (var0 == null) {
            return "";
        } else {
            try {
                return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(var0);
            } catch (Exception var2) {
                return var0.toString();
            }
        }
    }

    public static Date formatDate(String var0) {
        if (var0 == null) {
            return null;
        } else {
            try {
                return (new SimpleDateFormat("yyyy-MM-dd")).parse(var0);
            } catch (Exception var2) {
                return null;
            }
        }
    }

    public static java.sql.Date formatSqlDate(String var0) {
        return var0 != null && !var0.equals("") ? new java.sql.Date(formatDate(var0).getTime()) : null;
    }

    public static java.sql.Date formatSqlTime(String var0) {
        return var0 != null && !var0.equals("") ? new java.sql.Date(formatDateTime(var0).getTime()) : null;
    }

    public static int getMinutes(Date var0) {
        try {
            Calendar var1;
            (var1 = Calendar.getInstance()).setTime(var0);
            return var1.get(12) + var1.get(10) * 60 + var1.get(5) * 1440;
        } catch (Exception var2) {
            return 0;
        }
    }

    public static Date formatDateTime(String var0) {
        if (var0 == null) {
            return null;
        } else {
            try {
                return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(var0);
            } catch (Exception var2) {
                var2.printStackTrace();
                return null;
            }
        }
    }

    public static String processDateAddYear(String var0, int var1) {
        if (var0 == null) {
            return null;
        } else {
            try {
                SimpleDateFormat var2 = new SimpleDateFormat("yyyy-MM-dd");
                GregorianCalendar var3;
                (var3 = new GregorianCalendar()).setTime(formatDate(var0));
                var3.add(1, var1);
                return var2.format(var3.getTime());
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    public static String processDateAddMonth(String var0, int var1) {
        if (var0 == null) {
            return null;
        } else {
            try {
                SimpleDateFormat var2 = new SimpleDateFormat("yyyy-MM-dd");
                GregorianCalendar var3;
                (var3 = new GregorianCalendar()).setTime(formatDate(var0));
                var3.add(2, var1);
                return var2.format(var3.getTime());
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    public static String processDateAddDay(String var0, int var1) {
        if (var0 == null) {
            return null;
        } else {
            try {
                SimpleDateFormat var2 = new SimpleDateFormat("yyyy-MM-dd");
                GregorianCalendar var3;
                (var3 = new GregorianCalendar()).setTime(formatDate(var0));
                var3.add(5, var1);
                return var2.format(var3.getTime());
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        }
    }

    public static String processDateAddDay(String var0, String var1, String var2, int var3) {
        if (var0 == null) {
            return null;
        } else {
            try {
                GregorianCalendar var4;
                (var4 = new GregorianCalendar()).setTime((new SimpleDateFormat(var1)).parse(var0));
                var4.add(5, var3);
                return (new SimpleDateFormat(var2)).format(var4.getTime());
            } catch (Exception var5) {
                var5.printStackTrace();
                return null;
            }
        }
    }

    public static String processSQLDateAddOne(String var0) {
        if (var0 == null) {
            return null;
        } else {
            try {
                return "TO_DATE('" + var0 + "', 'yyyy-mm-dd') and TO_DATE('" + processDateAddDay(var0, 1) + "', 'yyyy-mm-dd')";
            } catch (Exception var1) {
                var1.printStackTrace();
                return null;
            }
        }
    }

    public static long getSecondsFromTwoDate(Date var0, Date var1) {
        return var1 != null && var0 != null ? (var1.getTime() - var0.getTime()) / 1000L : -1L;
    }

    public static long getMinutesFromTwoDate(Date var0, Date var1) {
        return var1 != null && var0 != null ? (var1.getTime() - var0.getTime()) / 60000L : -1L;
    }

    public static long getHoursFromTwoDate(Date var0, Date var1) {
        return var1 != null && var0 != null ? (var1.getTime() - var0.getTime()) / 3600000L : -1L;
    }

    public static long getDaysFromTwoDate(Date var0, Date var1) {
        return var1 != null && var0 != null ? (var1.getTime() - var0.getTime()) / 86400000L : -1L;
    }

    public static long getMonthsFromTwoDate(Date var0, Date var1) {
        GregorianCalendar var2;
        (var2 = new GregorianCalendar()).setTime(var0);
        GregorianCalendar var3;
        (var3 = new GregorianCalendar()).setTime(var1);
        return (long)((var3.get(1) - var2.get(1)) * 12 + var3.get(2) - var2.get(2));
    }

    public static long getYearsFromTwoDate(Date var0, Date var1) {
        GregorianCalendar var2;
        (var2 = new GregorianCalendar()).setTime(var0);
        GregorianCalendar var3;
        (var3 = new GregorianCalendar()).setTime(var1);
        return (long)(var3.get(1) - var2.get(1));
    }
}

