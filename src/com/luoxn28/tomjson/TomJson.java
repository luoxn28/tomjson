package com.luoxn28.tomjson;

import com.luoxn28.tomjson.deserializer.JsonObject;
import com.luoxn28.tomjson.serializer.JsonSerializer;
import com.luoxn28.tomjson.serializer.SerializerBuffer;

/**
 * TomJson - tomjson类
 *
 * @author luoxn28
 * @date 2016.7.21
 */
public class TomJson {

    // serializer配置类
    private static SerializerContext config = SerializerContext.instance();

    // ---------------------------------- Public Methods

    /**
     * Object转换为json字符串
     */
    public static String toJsonString(Object object) {
        SerializerBuffer buffer = new SerializerBuffer();

        JsonSerializer write = config.getObject(object.getClass());
        write.write(null, object, buffer);
        return buffer.toString();
    }

    /**
     * json字符串转换为JsonObject
     */
    public static JsonObject parseObject(String text) {
        return JsonObject.parseObject(text);
    }

    /**
     * json字符串转换为指定的Object
     */
    public static <T> T parseObject(String text, Class<T> clazz) {
        return JsonObject.parseObject(text, clazz);
    }

}
