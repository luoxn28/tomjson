package com.luoxn28.tomjson.deserializer;

/**
 * BooleanDeserializer - Boolean反序列化类
 *
 * @author luoxn28
 * @date 2016.7.23
 */
public class BooleanDeserializer implements JsonDeserializer {

    @Override
    public Object parse(String jsonValue) {
        return Boolean.parseBoolean(jsonValue);
    }

}
