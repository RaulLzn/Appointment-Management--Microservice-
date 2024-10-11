package upb.safi.AppointmentManagement.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Configuration class for setting up Redis in the application.
 * This class defines the necessary beans for connecting to a Redis server
 * and configuring a RedisTemplate for operations on Redis.
 */
@Configuration
public class RedisConfig {

    /**
     * Creates a RedisConnectionFactory bean that provides a connection to the Redis server.
     *
     * @return a LettuceConnectionFactory that serves as the connection factory for Redis.
     */
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    /**
     * Configures and creates a RedisTemplate bean for interacting with Redis.
     * The RedisTemplate is set up with a connection factory and a custom serializer
     * to handle serialization and deserialization of objects stored in Redis.
     *
     * @param redisConnectionFactory the RedisConnectionFactory to be used by the RedisTemplate.
     * @return a configured RedisTemplate instance.
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // Create a new RedisTemplate instance
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // Set the connection factory for the template
        template.setConnectionFactory(redisConnectionFactory);

        // Create an ObjectMapper for custom serialization
        ObjectMapper objectMapper = new ObjectMapper();
        // Register the JavaTimeModule to handle Java 8 Date and Time types
        objectMapper.registerModule(new JavaTimeModule());

        // Create a RedisSerializer using GenericJackson2JsonRedisSerializer
        RedisSerializer<Object> serializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        // Set the default serializer for the template
        template.setDefaultSerializer(serializer);

        return template; // Return the configured RedisTemplate
    }
}
