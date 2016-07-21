package com.luoxn28.tomjson.serializer;

/**
 * DoubleSerializer - Double序列化类
 *
 * @author luoxn28
 * @date 2016.7.21
 */
public class DoubleSerializer implements JsonSerializer {

    @Override
    public void write(String name, Object object, SerializerBuffer buffer) {
        buffer.append(object, "\"" + name + "\":" + object.toString());
    }

}
