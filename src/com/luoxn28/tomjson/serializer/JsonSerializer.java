package com.luoxn28.tomjson.serializer;

/**
 * JsonSerializer接口 - 所有XxxSerializer类的接口
 * 提供序列化方法
 *
 * @author luoxn28
 * @date 2016.7.21
 */
public interface JsonSerializer {

    /**
     *  json序列化方法
     * @param name   数据域名称
     * @param object 数据域的值
     * @param buffer json序列化缓存区
     */
    public void write(String name, Object object, SerializerBuffer buffer);

}
