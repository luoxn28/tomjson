package com.luoxn28.tomjson.serializer;

/**
 * BooleanSerializer - Boolean序列化类
 *
 * @author luoxn28
 * @date 2016.7.21
 */
public class BooleanSerializer implements JsonSerializer {

    @Override
    public void write(String name, Object object, SerializerBuffer buffer) {
        buffer.append(name, object, object.toString());
    }

}