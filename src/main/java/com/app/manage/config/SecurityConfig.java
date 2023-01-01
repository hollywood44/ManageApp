package com.app.manage.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
//                .antMatchers("/member/signIn","/member/signOut","/member/main").permitAll() // 모두 접근 허용
//                .antMatchers("/member/modifyMemberInfo/**","/member/modifyAuth/**").hasRole("Admin") // ROLE_admin 만 접근 허용
//                .antMatchers("/member/modifyMemberInfo/**").hasAnyRole("MemberManager","Admin") // ROLE_admin 만 접근 허용
//                .antMatchers("/product/push","/product/modify/**","/product/delete/**").hasAnyRole("ProductManager","Admin")
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin() // 로그인 관련 설정
                .loginPage("/member/signIn") //로그인 페이지
                .loginProcessingUrl("/member/signIn") //로그인 form의 action url 기본값은 '/login'
                .defaultSuccessUrl("/member/main") //로그인에 성공하면 이동할 페이지
                .and()
                .logout() // 로그아웃 설정
                .logoutUrl("member/signOut") //로그아웃 form의 action url
                .logoutSuccessUrl("/member/main") // 로그아웃 성공하면 이동할 페이지
                .invalidateHttpSession(true) // 세션 관련
                .and()
                .exceptionHandling() // 예외 핸들러
                .accessDeniedPage("/member/deniedPage"); // 권한없는 접근시 이동할 페이지

        http.csrf().disable();

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
