package com.hung.comicapi.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
//@EnableWebSecurity
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        UserDetails user = User.builder()
//                .username("chuduchung")
//                .password("$2a$10$NXwAeAWvQ8Wu1CxEY5OMPuz2t7PPHDXn4mt4gmD.ERpdlpNQD1Sbu")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("$2a$10$NXwAeAWvQ8Wu1CxEY5OMPuz2t7PPHDXn4mt4gmD.ERpdlpNQD1Sbu")
//                .roles("USER","ADMIN")
//                .build();
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(admin);
//        return jdbcUserDetailsManager;
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> {
            request.requestMatchers("/**").permitAll();
        });
        http.cors(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);

        http.httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
