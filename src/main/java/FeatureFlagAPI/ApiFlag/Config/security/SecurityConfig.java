package FeatureFlagAPI.ApiFlag.Config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
    
}
