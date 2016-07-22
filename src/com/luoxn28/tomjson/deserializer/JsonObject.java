package com.luoxn28.tomjson.deserializer;

import com.luoxn28.tomjson.DeserializerContext;
import com.luoxn28.tomjson.util.TinyUtil;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * JsonObject - json反序列化类
 *
 * @author luoxn28
 * @date 2016.7.22
 */
public class JsonObject {

    private static DeserializerContext config = DeserializerContext.instance();

    // ---------------------------------- Instance Variable

    // 存放反序列化中间结果
    private Map<String, Object> keyValue = new HashMap<>();

    // ---------------------------------- Public Methods

    public Set<Map.Entry<String, Object>> entrySet() {
        return keyValue.entrySet();
    }

    /**
     * 获取包含json字符串的tokens的JsonObject
     */
    public static JsonObject parseObject(String text) {
        JsonObject jsonObject = new JsonObject();

        if (!TinyUtil.isJsonFormatOk(text)) {
            return jsonObject;
        }

        //text = TinyUtil.trimBrace(text);
        List<String> tokens = getTokens(text);
        for (String ele : tokens) {
            String key = TinyUtil.getJsonKey(ele);
            String value = TinyUtil.getJsonValue(ele);
            jsonObject.keyValue.put(key, value);
        }
        return jsonObject;
    }

    /**
     * 解析json字符串，将结果存放到clazz实例中
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        List<String> tokens = getTokens(text);
        JsonObject jsonObject = new JsonObject();

        for (String token : tokens) {
            if (TinyUtil.isBeanKeyValue(token)) {
                parse(token, jsonObject.keyValue);
            }
            else if (TinyUtil.isJsonBean(token)) {
                String key = TinyUtil.getJsonKey(token);
                String valueStr = TinyUtil.getJsonValue(token);

                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    String name = field.getName();
                    if (name.equals(key)) {
                        String type = field.getGenericType().toString();
                        String className = type.substring(type.indexOf(' ') + 1, type.length());
                        Class subClazz = null;
                        try {
                            subClazz = Class.forName(type.substring(type.indexOf(' ') + 1, type.length()));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                            // break for (Field field : fields)
                            break;
                        }

                        try {
                            Object subObject = subClazz.newInstance();
                            subObject = parseObject(valueStr, subClazz);
                            jsonObject.keyValue.put(key, subObject);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else {
                System.out.println("I don't know how to do it :(");
            }
        }

        T object = null;

        try {
            object = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                PropertyDescriptor descriptor = new PropertyDescriptor(name, clazz);
                Method method = descriptor.getWriteMethod();

                if (jsonObject.keyValue.containsKey(name)) {
                    Object arg = jsonObject.keyValue.get(name);
                    method.invoke(object, arg);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return object;
        }
    }

    // ---------------------------------- Private Methods

    /**
     * parse单个token
     */
    private static void parse(String token, Map<String, Object> keyValue) {
        String key = TinyUtil.getJsonKey(token);
        String valueStr = TinyUtil.getJsonValue(token);

        Object value = config.getObject(valueStr);
        keyValue.put(key, value);
    }

    /**
     * 获取tokens 也就是以','为分隔符把text分成多个token
     * 注意：获取text中处于最上层的各个token
     */
    private static List<String> getTokens(String text) {
        List<String> tokens = new ArrayList<>();
        Stack<Character> flag = new Stack<>();

        text = text.trim();
        text = TinyUtil.trimBrace(text);
        for (int i = 0, length = text.length(), tokenIndex = 0; i < length; i++) {
            char c = text.charAt(i);
            if (c == '{' || c == '[') {
                flag.push(c);
            }
            else if (c == '}' || c == ']') {
                char tmp = flag.pop();
                if (!TinyUtil.flagOk(tmp, c)) {
                    System.out.println("json string format error");
                    return new ArrayList<>();
                }
            }

            if (c == ',' && flag.size() == 0) {
                tokens.add(text.substring(tokenIndex, i));
                tokenIndex = i + 1;
            }
            else if (i == length - 1 && flag.size() == 0) {
                tokens.add(text.substring(tokenIndex, length));
            }
        }

        return tokens;
    }

}
