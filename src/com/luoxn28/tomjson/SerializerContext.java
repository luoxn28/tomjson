package com.luoxn28.tomjson;

import com.luoxn28.tomjson.serializer.CollectionSerializer;
import com.luoxn28.tomjson.serializer.JsonSerializer;
import com.luoxn28.tomjson.serializer.ObjectSerializer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * SerializerContext - tomjson serializer配置类
 *
 * @author luoxn28
 * @date 2016.7.21
 */
public class SerializerContext {

    // 存放Class类型与json序列化类映射关系
    private static Map<Class, JsonSerializer> config = null;

    // objectSerializer序列化类
    private static ObjectSerializer objectSerializer = null;
    // Collection序列化类
    private static CollectionSerializer collectionSerializer = null;

    static {
        config = new HashMap<Class, JsonSerializer>();
    }

    private static SerializerContext context = new SerializerContext();

    // ---------------------------------- Constructors

    /**
     * 私有化构造方法
     */
    private SerializerContext() {
    }

    // ---------------------------------- Public Methods

    public static SerializerContext instance() {
        return context;
    }

    /**
     * 根绝不同Class获取对应的序列化类
     */
    public JsonSerializer getObject(Class clazz) {
        JsonSerializer write = objectSerializer;

        try {
            if (containClass(clazz)) {
                write = config.get(clazz);
            }
            else if (clazz.newInstance() instanceof Collection) {
                write = collectionSerializer;
            }
            else {
                write = objectSerializer;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return write;
        }
    }

    /**
     * context中是否包含Class key
     */
    public boolean containClass(Class key) {
        return config.containsKey(key);
    }

}
