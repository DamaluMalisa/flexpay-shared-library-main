package com.flex.payment.shared.config;

import com.flex.payment.shared.client.AuthFeignClient;
import com.flex.payment.shared.security.CustomAuditorAware;
import com.flex.payment.shared.security.TokenUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
public class AuditorAwareConfig {

    private final AuthFeignClient authFeignClient;
    private final TokenUtils tokenUtils;

    public AuditorAwareConfig(AuthFeignClient authFeignClient, TokenUtils tokenUtils) {
        this.authFeignClient = authFeignClient;
        this.tokenUtils = tokenUtils;
    }

    @Bean
    public AuditorAware<String> auditorAware() {
        return new CustomAuditorAware(tokenUtils);
    }



//    @Bean
//    public AuditorAware<UserDetails> auditorAware() {
//        return new CustomAuditorAware(authFeignClient, tokenUtils);
//    }
}
