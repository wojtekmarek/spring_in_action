package tacos.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth
                .inMemoryAuthentication()
                .withUser("buzz")
                .password("{noop}infiinity")
                .authorities("ROLE_USER")
                .and()
                .withUser("buzzo")
                .password("{noop}inf")
                .authorities("ROLE_USER");

    }
}
