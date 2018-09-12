package com.wey.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.wey.authentication.AuthenticationFailedHandler;
import com.wey.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailService;
    
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    
    @Autowired
    private AuthenticationFailedHandler authenticationFailedHandler;
    
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/index", "/login.html", "/login-error.html").permitAll() // 都可以访问
                .anyRequest().authenticated().antMatchers("/users/**").hasRole("ADMIN").and().formLogin() // 基于FORM表单的验证
                .loginPage("/login.html").failureUrl("/login-error.html").defaultSuccessUrl("/home/index").and()
                .rememberMe().tokenValiditySeconds(1209600).key("TOKEN").and().logout().logoutUrl("/logout")
                .logoutSuccessUrl("/logout-success").and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        
        // 登录成功的失败的Handler
        // .successHandler(authenticationSuccessHandler).failureHandler(authenticationFailedHandler)
        
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 静态资源地址
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
    }
    
    /**
     * 自定义认证策略
     */
    @Autowired
    public void configGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // auth.authenticationProvider(authProvider()).eraseCredentials(true);
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);
        System.out.println(passwordEncoder().encode("123456"));
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
