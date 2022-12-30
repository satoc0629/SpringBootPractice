package siersetup.infrastructure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.stereotype.Component;
import siersetup.filter.RenderingExceptionFilter;
import siersetup.handler.LoginSuccessHandler;

import java.util.Collection;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Component
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/login*","/css/*", "/js/*").permitAll().and()
                .authorizeRequests().anyRequest().authenticated().and()
                .headers().contentSecurityPolicy(
                        "default-src 'self' 'unsafe-inline' 'unsafe-eval' https: data:;"
                )
                .and()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and().formLogin(form -> {
                    form.loginPage("/login").permitAll().defaultSuccessUrl("/top")
                            .loginProcessingUrl("/login")
                            .usernameParameter("userId")
                            .passwordParameter("password").failureUrl("/login?error=true")
                            .successHandler(new LoginSuccessHandler())
                            .failureHandler((request, response, exception) -> {
                                log.error("authentication fail.", exception);
                                response.sendRedirect("/login?error=true");
                            });
                })
                .csrf()
                .and()
                .addFilterBefore(new RenderingExceptionFilter(), SecurityContextHolderFilter.class)
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passEncoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication()
                .passwordEncoder(passEncoder)
                .withUser("test")
                .password(passEncoder.encode("test"))
                .roles("EMPLOYEE");
    }

}
