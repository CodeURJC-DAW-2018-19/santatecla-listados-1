package com.urjc.daw.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationProviderUser authenticationProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //         PUBLIC VIEWS         //
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/sign_in").permitAll();
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("name");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/MainPage");
        http.formLogin().failureUrl("/login");

    //         PRIVATE VIEWS            //

        http.authorizeRequests().antMatchers("/MainPage").hasAnyRole("TEACHER","STUDENT");
        http.authorizeRequests().antMatchers("/deleteLessons/**").hasAnyRole("TEACHER");
        http.authorizeRequests().antMatchers("/deleteConcept/**").hasAnyRole("TEACHER");
        http.authorizeRequests().antMatchers("/lessonSearch").hasAnyRole("TEACHER","STUDENT");
        http.authorizeRequests().antMatchers("/lessonSearch/**").hasAnyRole("TEACHER","STUDENT");
        //http.authorizeRequests().antMatchers("/saveLesson").hasAnyRole("TEACHER");
        //http.authorizeRequests().antMatchers("/MainPage").hasAnyRole("STUDENT");

        http.authorizeRequests().antMatchers("/TeacherConcept_View").hasAnyRole("TEACHER");
        http.authorizeRequests().antMatchers("/StudentConceptView").hasAnyRole("STUDENT");




    }

    @Override
    public void configure(AuthenticationManagerBuilder authentication) throws Exception{
        authentication.authenticationProvider(authenticationProvider);
    }
}
