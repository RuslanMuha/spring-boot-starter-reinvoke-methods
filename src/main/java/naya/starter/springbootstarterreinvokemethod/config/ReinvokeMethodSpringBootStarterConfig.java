package naya.starter.springbootstarterreinvokemethod.config;

import naya.starter.springbootstarterreinvokemethod.aspect.ReinvokeMethodsThrowsException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReinvokeMethodSpringBootStarterConfig {

    @Bean
    public ReinvokeMethodsThrowsException reinvokeMethodsTrowsException(){
        return new ReinvokeMethodsThrowsException();
    }
}
