package lt.staupasedvinas.blog.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/public/**", "/login", "/register", "/", "/post/**").permitAll()
                .antMatchers("/private/**", "/create-post", "/edit-post", "/edit-comment", "/admin-panel", "/**").authenticated()
                //.antMatchers("/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .csrf()
                //TODO paklausti ar sitas legalus :D
                .disable()
                .formLogin()
                .permitAll()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // visa kita logika kuria mes nenorim apsaugoti
        web.ignoring().requestMatchers(
                PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
//                        .password("{noop}a") // password without encoding. Password as is plain text -> password is a
                .password("{bcrypt}$2y$10$Em6UwCBD5zprZnwXr6vBsuTGQxViYG9nAjBcHXmn1xFK9S6posTay")  // password with encoding. Password is a
                .roles("USER")
                .and()
                .withUser("admin")
                .password("{bcrypt}$2a$15$7HPzdEPDl16h9b6MJMbJsO7ylrJQn4xFgGf4m0.FVEa1V8hFGcC.a") // pass
                .roles("USER", "ADMIN");
    }
}
