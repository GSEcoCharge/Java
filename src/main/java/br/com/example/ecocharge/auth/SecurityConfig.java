// package br.com.example.ecocharge.auth;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;

// import br.com.example.ecocharge.service.UsuarioService;

// @Configuration
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http, UsuarioService usuarioService) throws Exception {
//         http
//             .authorizeHttpRequests(auth -> auth
//                     .anyRequest().permitAll())
//             .oauth2Login(login -> login
//                     .loginPage("/login")
//                     .defaultSuccessUrl("/", true)
//                     .userInfoEndpoint(userInfo -> userInfo.userService(usuarioService))
//                     .permitAll())
//             ;
//                 return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }


// }