import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Configuration
@EnableFeignClients
public class FeignConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Decoder feignDecoder() {
        return new JacksonDecoder();
    }

    @Bean
    public Encoder feignEncoder() {
        return new JacksonEncoder();
    }

    @Bean
    public Log4jLoggerFactory loggerFactory() {
        return new Log4jLoggerFactory();
    }
}
