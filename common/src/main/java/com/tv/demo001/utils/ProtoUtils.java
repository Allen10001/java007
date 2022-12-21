package com.tv.demo001.utils;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * @author hubo88
 * @description proto序列化工具类
 * @date 2022/12/20
 */
public class ProtoUtils<T> {

    /**
     * protobuff 序列化
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> byte[] protoStuffSerialize(T obj) {
        if (obj == null) {
            throw new RuntimeException("序列化對象失败(" + obj + ")!");
        }
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) RuntimeSchema.getSchema(obj.getClass());
        LinkedBuffer buffer = LinkedBuffer.allocate(1024 * 1024);
        byte[] protostuff = null;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new RuntimeException("序列化(" + obj.getClass() + ")對象(" + obj + ")發生異常!", e);
        } finally {
            buffer.clear();
        }
        return protostuff;
    }

    /**
     * protobuff 反序列化
     *
     * @param paramArrayOfByte
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T protoStuffDeserialize(byte[] paramArrayOfByte, Class<T> targetClass) {
        if (paramArrayOfByte == null || paramArrayOfByte.length == 0) {
            throw new RuntimeException("反序列化對象發生異常,byte序列爲空!");
        }
        T instance = null;
        try {
            instance = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("反序列化過程當中依據類型建立對象失敗!", e);
        }
        Schema<T> schema = RuntimeSchema.getSchema(targetClass);
        ProtostuffIOUtil.mergeFrom(paramArrayOfByte, instance, schema);
        return instance;
    }
}
