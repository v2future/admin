package com.hollycrm.mi.core.kit;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 常用工具方法
 */
public class MiKit {

    private static final Logger LOG = LoggerFactory.getLogger(MiKit.class);

    /**
     * 生成uuid
     * @return
     */
    public static String generateUUID( ) {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成简短的序列号，高并发下不保证不重复
     * @return
     */
    public static String generateSN () {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        DecimalFormat decimalFormat = new DecimalFormat("0000");
        Random random = new Random();
        return dateFormat.format(new Date()) + decimalFormat.format(random.nextInt(9999));
    }

    /**
     * 获取yyyy-MM-dd格式的当前日期
     * @return
     */
    public static String getCurrentDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format( new Date());
    }

    /**
     * 获取yyyy-MM-dd HH:mm:ss格式的当前时间
     * @return
     */
    public static String getCurrentTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format( new Date());
    }

    /**
     * 除法，保留2位小数
     * 截取模式为四舍五入
     * 例子：1/3，输出为33.33
     * @param fenzi
     * @param fenmu
     * @return
     */
    public static String divide(String fenzi, String fenmu) {
        if ( fenzi == null || fenmu == null || "0".equals(fenmu))
            return "";
        try {
            DecimalFormat df = new DecimalFormat("0.00");
            return df.format( new BigDecimal(fenzi).divide( new BigDecimal(fenmu), 4, BigDecimal.ROUND_HALF_UP).doubleValue() * 100);
        } catch(Exception e) {
            e.printStackTrace();
            //
        }
        return "";
    }

    public static boolean gt(String src, String target) {
        if ( src == null || target == null)
            return false;
        return src.compareTo(target) > 0;
    }

    public static boolean lt(String src, String target) {
        if ( src == null || target == null)
            return false;
        return src.compareTo(target) < 0;
    }

    private static final char HEX_DIGITS[] = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    public static String getMD5String(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            bytes = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(HEX_DIGITS[(bytes[i] & 0xf0) >> 4] + ""
                        + HEX_DIGITS[bytes[i] & 0xf]);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            //impossible
        }
        return "";
    }

    /**
     * 分页页面对象
     * @param code
     * @param msg
     * @return
     */
    public static String getDefaultPageJson(String code, String msg){
        StringBuilder buffer = new StringBuilder();
        buffer.append("{\"code\":\"").append(code).
                append("\", msg:\"").append(msg).append("\", data:[]}");
        return buffer.toString();

    }

    /**
     * 普通页面对象
     * @param success
     * @param msg
     * @return
     */
    public static  String getDefaultJson(boolean success, String msg){
        StringBuilder buffer = new StringBuilder();
        buffer.append("{\"success\":").append(success).
                append(",\"msg\":\"").append(msg).append("\"}");
        return buffer.toString();
    }

    public static void copyProperties(Object target, Object source){
        try {
            BeanUtils.copyProperties(target, source);
        }catch (Exception e) {
            LOG.error("对象copy异常", e);
        }
    }

}
