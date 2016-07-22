package com.luoxn28.tomjson;

import com.luoxn28.tomjson.deserializer.*;
import com.luoxn28.tomjson.util.TinyUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * DeserializerContext - TinyJson deserializer配置类
 *
 * @author luoxn28
 * @date 2016.7.22
 */
public class DeserializerContext {

    private static DeserializerContext context = new DeserializerContext();

    // 存放Class类型与json反序列化类映射关系
    private static Map<Class, JsonDeserializer> config = null;

    static {
        config = new HashMap<Class, JsonDeserializer>();

        config.put(String.class, new StringDeserializer());
        config.put(Integer.class, new IntegerDeserializer());
        config.put(Long.class, new LongDeserializer());
        config.put(Double.class, new DoubleDeserializer());
    }

    // ---------------------------------- Constructors

    /**
     * 私有化构造方法
     */
    private DeserializerContext() {
    }

    // ---------------------------------- Public Methods

    public static DeserializerContext instance() {
        return context;
    }

    /**
     * 根据json字符串返回对应的Class类型
     */
    public Object getObject(String jsonValue) {
        JsonDeserializer parse = null;

        if (TinyUtil.isString(jsonValue)) {
            parse = config.get(String.class);
        }
        else if (TinyUtil.isInteger(jsonValue)) {
            parse = config.get(Integer.class);
        }
        else if (TinyUtil.isLong(jsonValue)) {
            parse = config.get(Long.class);
        }
        else if (TinyUtil.isDouble(jsonValue)) {
            parse = config.get(Double.class);
        }
        else {
            // Not come here.
        }

        return parse.parse(jsonValue);
    }

}
