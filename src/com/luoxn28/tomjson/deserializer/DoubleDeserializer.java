package com.luoxn28.tomjson.deserializer;

/**
 * DoubleDeserializer - Double反序列化类
 *
 * @author luoxn28
 * @date 2016.7.22
 */
public class DoubleDeserializer implements JsonDeserializer {

    @Override
    public Object parse(String jsonValue) {
        return Double.parseDouble(jsonValue);
    }

}
