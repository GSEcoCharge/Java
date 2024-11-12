// package br.com.example.ecocharge.auth;

// import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// public class AuthController {
//     private final AuthService authService;

//     public AuthController(AuthService authService) {
//         this.authService = authService;
//     }

//     @PostMapping("/login")
//     public Token login(@RequestBody Credentials credentials) {
//         return authService.login(credentials);
//     }

//     @PostMapping("/login/oauth2")
//     public Token loginOAuth2(@AuthenticationPrincipal OAuth2User principal) {
//         return authService.loginOAuth2(principal);
//     }

// }
