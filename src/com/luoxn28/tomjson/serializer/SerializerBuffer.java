package com.luoxn28.tomjson.serializer;

import com.luoxn28.tomjson.SerializerContext;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * SerializerBuffer类 - 缓存json序列化中间结果
 *
 * @author luoxn28
 * @date 2016.7.21
 */
public class SerializerBuffer {

    // ---------------------------------- Instance Variable

    // SerializerContext配置类
    SerializerContext config = SerializerContext.instance();

    // json序列化中间结果缓冲区 <Class, String>
    private Map<Object, String> buffer = null;

    // ---------------------------------- Constructors

    public SerializerBuffer() {
        buffer = new IdentityHashMap<Object, String>();
    }

    // ---------------------------------- Public Methods

    /**
     * 使用默认json数据项格式 - "name":"value"
     */
    public void append(String name, Object object, String value) {
        buffer.put(object, "\"" + name + "\":\"" + value + "\"");
    }

    /**
     * 使用自定义json数据格式 - "xxx":xxx
     */
    public void append(Object object, String jsonValue) {
        buffer.put(object, jsonValue);
    }

    @Override
    public String toString() {
        StringBuffer json = new StringBuffer();

        json.append("{");
        json.append(toStringNoWrapper());
        json.append("}");

        return json.toString();
    }

    public String toStringNoWrapper() {
        StringBuffer json = new StringBuffer();
        boolean isFirst = true;

        for (Map.Entry<Object, String> ele : buffer.entrySet()) {
            if (isFirst) {
                isFirst = false;
            }
            else {
                json.append(",");
            }

            Object object = ele.getKey();
            json.append(buffer.get(object));
        }

        return json.toString();
    }

}
