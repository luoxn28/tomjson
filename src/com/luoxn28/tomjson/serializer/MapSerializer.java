package com.luoxn28.tomjson.serializer;

import com.luoxn28.tomjson.SerializerContext;

import java.util.Map;

/**
 * MapSerializer - Map序列化类
 *
 * @author luoxn28
 * @date 2016.7.29
 */
public class MapSerializer implements JsonSerializer {

    /**
     * serializer配置类
     */
    private static SerializerContext config = SerializerContext.instance();

    @Override
    public void write(String name, Object object, SerializerBuffer buffer) {
        SerializerBuffer subBuffer = new SerializerBuffer();
        Map map = (Map) object;

        for (Object key : map.keySet()) {
            Object value = map.get(key);
            JsonSerializer write = config.getObject(value.getClass());
            write.write(key.toString(), value, subBuffer);
        }
        buffer.append(object, "\"" + name + "\":" + subBuffer.toString());
    }

}
