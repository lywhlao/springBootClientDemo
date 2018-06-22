package com.example.demo.serializer;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class FastJsonSerializer implements RedisSerializer {

    @Override
    public byte[] serialize( Object o) throws SerializationException {
        if(o==null){
            return null;
        }
        return JSON.toJSONBytes(o);
    }

    @Override
    public Object deserialize( byte[] bytes) throws SerializationException {
        if(bytes==null){
            return null;
        }
        return JSON.parse(bytes);
    }
}
