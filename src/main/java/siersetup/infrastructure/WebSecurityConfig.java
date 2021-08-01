package siersetup.infrastructure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .authorizeRequests().anyRequest().permitAll().and()
                .authorizeRequests().anyRequest().authenticated().and()
//                .authorizeRequests().antMatchers("/login").permitAll().and()
                .headers().contentSecurityPolicy(
                        "default-src 'self' 'unsafe-inline' 'unsafe-eval' https: data:;"
                )
                .and()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and().formLogin()
                .defaultSuccessUrl("/top")
//                .loginPage("/login")
                .loginProcessingUrl("/login")
                .and()
                .csrf()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passEncoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication()
                .passwordEncoder(passEncoder)
                .withUser("user")
                .password(passEncoder.encode("test"))
                .roles("USER");
    }
}
