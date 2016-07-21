package com.luoxn28.tomjson.serializer;

import java.util.Collection;

/**
 * CollectionSerializer - Collection序列化类(List/Set/Queue)
 *
 * @author luoxn28
 * @date 2016.7.21
 */
public class CollectionSerializer implements JsonSerializer {

    @Override
    public void write(String name, Object object, SerializerBuffer buffer) {
        SerializerBuffer subBuffer = new SerializerBuffer();
        Collection collection = (Collection) object;
        for (Object ele : collection) {
            SerializerBuffer subBuffer2 = new SerializerBuffer();
            ObjectSerializer write = new ObjectSerializer();
            write.write(null, ele, subBuffer2);
            subBuffer.append(ele, subBuffer2.toString());
        }

        String value = "[" + subBuffer.toStringNoWrapper() + "]";
        buffer.append(object, "\"" + name + "\":" + value);
    }

}
