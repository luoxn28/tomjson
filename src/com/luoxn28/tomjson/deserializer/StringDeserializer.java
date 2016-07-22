package com.luoxn28.tomjson.deserializer;

import com.luoxn28.tomjson.util.TinyUtil;

/**
 * StringDeserializer - String反序列化类
 *
 * @author luoxn28
 * @date 2016.7.22
 */
public class StringDeserializer implements JsonDeserializer {

    @Override
    public Object parse(String jsonValue) {
        return TinyUtil.trimQuote(jsonValue);
    }

}
