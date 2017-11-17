package tools.util;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class JDKRedisTemplate extends org.springframework.data.redis.core.RedisTemplate<String, Object> {

    public JDKRedisTemplate() {
        JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
        setKeySerializer(new StringRedisSerializer());
        setValueSerializer(serializer);
        setHashKeySerializer(new StringRedisSerializer());
        setHashValueSerializer(serializer);
    }

    @Override
    public void setConnectionFactory(RedisConnectionFactory connectionFactory) {
        super.setConnectionFactory(connectionFactory);
    }
}
