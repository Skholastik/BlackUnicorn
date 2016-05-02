package com.springapp.mvc.Config;




import com.springapp.mvc.Config.CustomSecure.CsrfHeaderFilter;
import com.springapp.mvc.Config.CustomSecure.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*    @Autowired
    AuthFailure authFailure;

    @Autowired
    AuthSuccess authSuccess;*/




    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    public void configAuthBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailService);
        builder.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        return authenticationProvider;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                  /*  .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                .and()*/
                .formLogin()
                .permitAll()
                .and()
                .authorizeRequests()
                    .antMatchers("/admin")
                    .hasRole("ADMIN")
                    .antMatchers("/admin/*")
                    .hasRole("ADMIN")
                .antMatchers("/login")
                .permitAll()
                .antMatchers("/")
                .permitAll();
                /*.and()
                .httpBasic().disable()
                .addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
                .csrf().csrfTokenRepository(csrfTokenRepository());*/


    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        repository.setParameterName("_csrf");
        return repository;
    }
}
