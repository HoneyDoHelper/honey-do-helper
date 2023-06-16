package com.codeup.honeydohelper;

import com.codeup.honeydohelper.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                        /* Pages that require authentication
                         * only authenticated users can create and edit ads */
                        .requestMatchers("users/{id}/edit", // Only authenticated users can edit profiles
                                "/services/honeydoerProfile", "/services/bookService/{id1}/{id2}",
                                "/dashboard")
                        .authenticated()
                        .requestMatchers(
                                "/",
                                "/index",
                                "/about",
                                "/contact",
                                "/support",
                                "/authentication/register",
                                "/authentication/**",
                                "/register",
                                "/register/user",
                                "/register/honeydoer",
                                "/register/honeydoer/**",
                                "/add/skills",
                                "/user/honeydoer/dashboard/**",
                                "/services",
                                "/services/**",
                                "/apis/**",
                                "/login",
                                "/categories",
                                "/categories/**",
                                "/tasks/**",
                                "/tasks/update",
                                "/edit/profile/**",
                                "/delete/profile",
                                "/edit/**",
                                "/edit/skills/**",
                                "/delete/skills/**",
                                "/static/**",
                                "/error").permitAll()
                        // allow loading of static resources
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
                )
                /* Login configuration */
                .formLogin((login) -> login.loginPage("/login").usernameParameter("email").defaultSuccessUrl("/dashboard"))
                /* Logout configuration */
                .logout((logout) -> logout.logoutSuccessUrl("/login?logout"))
                .httpBasic(withDefaults());
        return http.build();
    }
}