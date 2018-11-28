package com.csd.study.distribute.lock;

import org.I0Itec.zkclient.exception.ZkMarshallingError;
import org.I0Itec.zkclient.serialize.ZkSerializer;

import java.io.UnsupportedEncodingException;

public class MyZkSerializer implements ZkSerializer {

    String charset = "UTF-8";

    // 序列化使用字符集将对象转成相应字节
    public byte[] serialize(Object o) throws ZkMarshallingError {
        try {
            return String.valueOf(o).getBytes(charset);
        } catch (UnsupportedEncodingException e){
            throw new ZkMarshallingError();
        }
    }

    public Object deserialize(byte[] bytes) throws ZkMarshallingError {
        try {
            return new String(bytes,charset);
        } catch (UnsupportedEncodingException e) {
            throw new ZkMarshallingError();
        }
    }
}
