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
        http

                // Login configuration
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/dashboard") // Redirect to the user's home page after successful login (can be any URL)
                .permitAll() // Allow anyone to access the login page

                // Logout configuration
                .and()
                .logout()
                .logoutSuccessUrl("/login") // Redirect to the login page after successful logout (with a query string value appended)

                // Pages that require authentication
                .and()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/users/{id}/edit", // Only authenticated users can edit profiles
                        "/services/honeydoerProfile",
                        "/dashboard"// Only authenticated honeydoers can view their profiles
                )
                .authenticated()

                // Pages that can be viewed without authentication
                .and()
                .authorizeHttpRequests()
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
                        "/categories",
                        "/categories/**",
                        "/tasks/**",
                        "/tasks/update",
                        "/edit/profile/**",
                        "/edit/**",
                        "/edit/skills/**",
                        "/delete/skills/**",
                        "/static/**",
                        "/css/**",
                        "/img/**",
                        "/error")
                .permitAll();

        return http.build();
    }

}