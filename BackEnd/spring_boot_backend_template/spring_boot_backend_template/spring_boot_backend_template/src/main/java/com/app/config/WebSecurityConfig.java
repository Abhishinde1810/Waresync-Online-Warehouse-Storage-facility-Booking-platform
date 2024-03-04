package com.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.security.CustomUserDetailService;
import com.app.security.JwtAuthEntryPoint;
import com.app.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private JwtAuthEntryPoint jwtEntryPoint;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .anyRequest().permitAll().and()
//                .antMatchers("/api/login")
//                .permitAll()
//                .antMatchers("/owner/authowner")
//                .permitAll()
//                .antMatchers("/owner/addowner")
//                .permitAll()
//                .antMatchers("/owner")
//                .permitAll()
//                .antMatchers("/warehouse/adddata") // Exclude /warehouse/adddata from authentication
//                .permitAll()
//                .antMatchers("/warehouse/getwarehouse")
//                .permitAll()
//                .antMatchers("/warehouse/ware/{id}")
//                .permitAll()
//                .antMatchers("/warehouse/delete/{warehouseId}")
//                .permitAll()
//                .antMatchers("/update")
//                .permitAll()
//                .anyRequest().authenticated()
//                .and()
            .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @SuppressWarnings("deprecation")
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
