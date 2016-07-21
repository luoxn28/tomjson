package com.luoxn28.tomjson.serializer;

/**
 * StringSerializer - String序列化类
 *
 * @author luoxn28
 * @date 2016.7.21
 */
public class StringSerializer implements JsonSerializer {

    @Override
    public void write(String name, Object object, SerializerBuffer buffer) {
        buffer.append(name, object, object.toString());
    }

}