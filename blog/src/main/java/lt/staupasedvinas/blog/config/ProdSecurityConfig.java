package lt.staupasedvinas.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@Profile("prod")
public class ProdSecurityConfig extends SecurityConfig {
    public ProdSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, LogoutSuccessHandler logoutSuccessHandler) {
        super(userDetailsService, passwordEncoder, logoutSuccessHandler);
    }

}
