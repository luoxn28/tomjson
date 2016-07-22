package com.luoxn28.tomjson.deserializer;

/**
 * IntegerDeserializer - Integer类型反序列化类
 *
 * @author luoxn28
 * @date 2016.7.22
 */
public class IntegerDeserializer implements JsonDeserializer {

    @Override
    public Object parse(String jsonValue) {
        return Integer.parseInt(jsonValue);
    }

}
