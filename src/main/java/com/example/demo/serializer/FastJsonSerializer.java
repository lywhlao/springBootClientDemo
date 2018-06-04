package com.example.demo.serializer;

import com.alibaba.fastjson.JSON;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

public class FastJsonSerializer implements RedisSerializer {

    @Nullable
    @Override
    public byte[] serialize(@Nullable Object o) throws SerializationException {
        if(o==null){
            return null;
        }
        return JSON.toJSONBytes(o);
    }

    @Nullable
    @Override
    public Object deserialize(@Nullable byte[] bytes) throws SerializationException {
        if(bytes==null){
            return null;
        }
        return JSON.parse(bytes);
    }
}
