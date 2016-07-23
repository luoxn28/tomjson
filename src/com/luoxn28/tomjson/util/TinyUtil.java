package com.luoxn28.tomjson.util;

import java.util.regex.Pattern;

/**
 * TinyUtil - 提供一些实用方法
 *
 * @author luoxn28
 * @date 2016.7.22
 */
public class TinyUtil {

    /**
     * 判断字符串是否是json字符串格式 "xxx"
     */
    public static boolean isString(String str) {
        return (str != null && str.charAt(0) == '"' && str.charAt(str.length() - 1) == '"');
    }

    /**
     * 判断字符串是否是整数 Integer (MAX_VALUE: 2147483647)
     * 1到9位整数认为是Integer型
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]{1,9}$");
        return (str != null && pattern.matcher(str).matches());
    }

    /**
     * 判断字符串是否是长整型 Long
     * 10位及以上认为是Long型
     */
    public static boolean isLong(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]{10}$");
        return (str != null && pattern.matcher(str).matches());
    }

    /**
     * 判断字符串是否是浮点型 Float Double
     */
    public static boolean isFloat(String str) {
        return isDouble(str);
    }
    public static boolean isDouble(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]+$");
        return (str != null && pattern.matcher(str).matches());
    }

    /**
     * 判断字符串是否是单纯的json键值对 "name":"luoxn28"
     */
    public static boolean isBeanKeyValue(String text) {
        if (text.indexOf('{') != -1 || text.indexOf('}') != -1) {
            return false;
        }
        else if (text.indexOf('[') != -1 || text.indexOf(']') != -1) {
            return false;
        }
        else if (text.indexOf('(') != -1 || text.indexOf(')') != -1) {
            return false;
        }
        else {
            return (text.indexOf(':') != -1);
        }
    }

    /**
     * 判断字符串是否是单纯的json Bean类 "car":{"price":10000}
     */
    public static boolean isJsonBean(String text) {
        String value = getJsonValue(text);
        if (!flagOk(value.charAt(0), value.charAt(value.length() - 1))) {
            return false;
        }

        if (isBeanKeyValue(trimBrace(value))) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 判断字符串是否是集合类
     */
    public static boolean isJsonCollectionBean(String text) {
        String value = getJsonValue(text);
        if (!flagOk(value.charAt(0), value.charAt(value.length() - 1))) {
            return false;
        }

        return (value.charAt(0) == '[' && value.charAt(value.length() - 1) == ']');
    }

    /**
     * 判断json字符串格式是否正确
     */
    public static boolean isJsonFormatOk(String text) {
        return true;
    }

    /**
     * 获取第一个 : 后的字符串
     */
    public static String getJsonValue(String text) {
        return text.substring(text.indexOf(':') + 1, text.length());
    }

    /**
     * 获取第一个 : 前的字符串，不包括""
     */
    public static String getJsonKey(String text) {
        return trimQuote(text.substring(0, text.indexOf(':')));
    }

    /**
     * 去除text中最外侧的 ""
     */
    public static String trimQuote(String text) {
        if (text == null) {
            return "";
        }
        text = text.trim();
        int left = 0;
        int right = text.length() - 1;

        if (text.charAt(0) == '"' && text.charAt(text.length() - 1) == '"') {
            return text.substring(left + 1, right);
        }
        else {
            return text;
        }
    }

    /**
     * 去除text中的最外侧的 {}
     */
    public static String trimBrace(String text) {
        if (text == null) {
            return "";
        }
        text = text.trim();
        int left = 0;
        int right = text.length() - 1;

        if (text.charAt(0) == '{' && text.charAt(text.length() - 1) == '}') {
            return text.substring(left + 1, right);
        }
        else {
            return text;
        }
    }

    /**
     * 去除text中最外侧的 []
     */
    public static String trimSquare(String text) {
        if (text == null) {
            return "";
        }
        text = text.trim();
        int left = 0;
        int right = text.length() - 1;

        if (text.charAt(0) == '[' && text.charAt(text.length() - 1) == ']') {
            return text.substring(left + 1, right);
        }
        else {
            return text;
        }
    }

    /**
     * 判断c1和c2字符是否匹配
     * '{'与'}', '['与']'
     */
    public static boolean flagOk(char c1, char c2) {
        if (c1 == '{' && c2 == '}') {
            return true;
        }
        else if (c1 == '}' && c2 == '{') {
            return true;
        }
        else if (c1 == '[' && c2 == ']') {
            return true;
        }
        else if (c1 == '(' && c2 == ')') {
            return true;
        }

        return false;
    }

}
