package com.luoxn28.tomjson.serializer;

import com.luoxn28.tomjson.SerializerContext;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * ObjectSerializer - Object序列化类
 *
 * @author luoxn28
 * @date 2016.7.15
 */
public class ObjectSerializer implements JsonSerializer {

    private static SerializerContext config = SerializerContext.instance();

    @Override
    public void write(String name, Object object, SerializerBuffer buffer) {
        try {
            // name为null表示是Root Object
            if (name == null) {
               Class clazz = object.getClass();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    String key = field.getName();
                    PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
                    Method method = descriptor.getReadMethod();
                    Object value = method.invoke(object);

                    JsonSerializer write = config.getObject(value.getClass());
                    write.write(key, value, buffer);
                }
            }
            else {
                SerializerBuffer subBuffer = new SerializerBuffer();

                write(null, object, subBuffer);
                buffer.append(object, "\"" + name + "\":" + subBuffer.toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
