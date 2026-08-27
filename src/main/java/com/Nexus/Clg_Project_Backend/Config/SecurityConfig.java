package com.Nexus.Clg_Project_Backend.Config;

import com.Nexus.Clg_Project_Backend.Service.UserService.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(
            CustomUserDetailService userDetailService, PasswordEncoder passwordEncoder
    ){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }

    @Bean
    public SecurityFilterChain SecurityFilterChain(
            HttpSecurity http, DaoAuthenticationProvider provider, JwtAuthenticationConverter jwtAuthenticationConverter) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authenticationProvider(provider)


                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                                .requestMatchers(
                                        "/api/register",
                                        "/api/login").permitAll()
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                                // Lecturer-only writes — same as before.
                                .requestMatchers(HttpMethod.POST, "/api/notice/**").hasAnyRole("LECTURER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/notice/**").hasAnyRole("LECTURER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/notice/**").hasAnyRole("LECTURER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/notes/**").hasAnyRole("LECTURER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/notes/**").hasAnyRole("LECTURER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/old-question-papers/**").hasAnyRole("LECTURER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/old-question-papers/**").hasAnyRole("LECTURER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/tests").hasAnyRole("LECTURER", "ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/tests/**").hasAnyRole("LECTURER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/tests/**").hasAnyRole("LECTURER", "ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/tests/mine").hasAnyRole("LECTURER", "ADMIN")

                                .requestMatchers(HttpMethod.POST, "/api/tests/*/submit").hasRole("USER")

                                .requestMatchers("/api/admin/**").hasRole("ADMIN")

                                .anyRequest().authenticated()
                )





                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .oauth2ResourceServer(
                        oauth2 ->
                                oauth2.jwt(jwt ->
                                                jwt.jwtAuthenticationConverter(
                                                        jwtAuthenticationConverter
                                                )
                                        )
                )
        ;
        return http.build();
    }

    @Bean
    public SecretKey jwtSecretKey(
            @Value("${jwt.secret}") String secret){

        byte[] decodedkey = Base64.getDecoder().decode(secret);
        return new SecretKeySpec(decodedkey, "HmacSHA256");
    }

    @Bean
    public AuthenticationManager authenticationManager(DaoAuthenticationProvider authenticationProvider){
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public JwtAuthenticationConverter  jwtAuthenticationConverter(){

        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();

        authoritiesConverter.setAuthoritiesClaimName("authorities");

        authoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(authoritiesConverter);

        return  authenticationConverter;
    }

    @Bean
    public JwtEncoder  jwtEncoder(SecretKey secretKey){

        return NimbusJwtEncoder
                .withSecretKey(secretKey)
                .algorithm(MacAlgorithm.HS256)
                .build();
    }


    @Bean
    public JwtDecoder jwtDecoder(SecretKey secretKey, @Value("${jwt.issuer}") String issuer){
        NimbusJwtDecoder decoder = NimbusJwtDecoder
                .withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();

        decoder.setJwtValidator(
                JwtValidators.createDefaultWithIssuer(issuer)
        );

        return decoder;
    }
}
