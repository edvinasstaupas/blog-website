package lt.staupasedvinas.blog.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@Profile("dev")
public class DevSecurityConfig extends SecurityConfig {

    public DevSecurityConfig(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, LogoutSuccessHandler logoutSuccessHandler) {
        super(userDetailsService, passwordEncoder, logoutSuccessHandler);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(
                PathRequest.toStaticResources().atCommonLocations(),
                PathRequest.toH2Console());
    }
}

