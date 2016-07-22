package com.luoxn28.tomjson.deserializer;

/**
 * JsonDeserializer - 所有XxxDeserializer类的接口
 *
 * @author luoxn28
 * @date 2016.7.22
 */
public interface JsonDeserializer {

    /**
     * json解析方法
     * @param jsonValue json字符串，类似于"xxx2" "(xxx1":"xxx2")
     * @return 返回该json字符串所属的类型
     */
    Object parse(String jsonValue);

}
