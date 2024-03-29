package com.dano.kjm.global.config;

import com.dano.kjm.global.config.handler.LoginFailHandler;
import com.dano.kjm.domain.member.dao.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberRepository memberRepository;
//    private final DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(memberRepository);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler(){
        return new LoginFailHandler();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginPage("/members/login")
                .usernameParameter("email")
                .passwordParameter("password")
//                .successForwardUrl("/")
                .defaultSuccessUrl("/", true)
                .failureHandler(authenticationFailureHandler());
//                .failureUrl("/members/member/error");

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                        .invalidateHttpSession(false);

        http.rememberMe()
                .rememberMeParameter("remember-me")
                .userDetailsService(userDetailsService());
//                .tokenRepository(tokenRepository());

        http.authorizeRequests()
                .mvcMatchers("/", "/members/login","/members/login/error",
                        "/members/sign-up", "/items").permitAll()
                .antMatchers("/manager/**").hasRole("MANAGER")
                .anyRequest().authenticated();
        return http.build();
    }

//    @Bean
//    public PersistentTokenRepository tokenRepository() {
//        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
//        jdbcTokenRepository.setDataSource(dataSource);
//
//        return jdbcTokenRepository;
//    }

    @Bean
    @Order(0)
    public SecurityFilterChain resources(HttpSecurity http) throws Exception{
        http
                .requestMatchers((matchers) -> matchers.antMatchers(
                        "/css/**","/js/**", "/partials/**",
                        "/phantom-main/**","/plugins/**", "/screenshots/**"
                        ,"/scss/**"))
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
                .requestCache().disable()
                .securityContext().disable()
                .sessionManagement().disable();
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }
}
