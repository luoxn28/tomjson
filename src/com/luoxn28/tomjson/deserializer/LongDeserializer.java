package com.luoxn28.tomjson.deserializer;

/**
 * LongDeserializer - Long反序列化类
 *
 * @author luoxn28
 * @date 2016.7.22
 */
public class LongDeserializer implements JsonDeserializer {

    @Override
    public Object parse(String jsonValue) {
        return Long.parseLong(jsonValue);
    }

}
