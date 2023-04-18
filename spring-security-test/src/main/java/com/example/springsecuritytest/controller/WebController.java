package com.example.springsecuritytest.controller;

import com.example.springsecuritytest.entity.UserCred;
import com.example.springsecuritytest.service.LoadUsers;
import com.example.springsecuritytest.service.UserCredRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.*;

@Configuration
@RestController
@EnableWebSecurity
@EnableMethodSecurity
public class WebController {

    @Bean
    public UserDetailsService userDetailsService(){
        /*UserDetails admin= User.withUsername("shrik").password(encoder.encode("welcome1")).roles("ADMIN").build();
        UserDetails user= User.withUsername("prajna").password(encoder.encode("welcome1")).roles("USER").build();
        return new InMemoryUserDetailsManager(admin,user);*/
        return new UserCredRepositoryService();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeHttpRequests().requestMatchers("/index","/loadUser").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/greeting/*")
                .authenticated().and().httpBasic()  //.formLogin()
                .and()
                .authorizeHttpRequests().requestMatchers("/admin")
                .authenticated().and().httpBasic()
                .and()//.formLogin()
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }
    @Value("${my.greeting:Hi!!!}")
    private String greetingMsg;
    @PreAuthorize("hasAuthority('ROLE_USER')")
    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable("name") String name){
        return greetingMsg + name ;
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String getAdminPage(){
        return "This is from admin";
    }
    @Autowired
    private LoadUsers addnew;
    @PostMapping("/loadUser")
    public ResponseEntity addNewUser(@RequestBody UserCred user){
        return new ResponseEntity<String>(addnew.addUser(user), HttpStatus.OK);

    }

}