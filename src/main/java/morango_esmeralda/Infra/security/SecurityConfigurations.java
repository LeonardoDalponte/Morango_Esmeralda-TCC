package morango_esmeralda.Infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors().and()
                .csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "api/produtos").permitAll()
                        .requestMatchers(HttpMethod.PUT, "api/produtos/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/produtos/{id}/imagem").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/produtos/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/produtos/buscar-todos").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "api/produtos/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/cadastrar").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/carrinhos/adcionarProduto").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/usuarios/buscar-por-token").permitAll()
                        .anyRequest().authenticated()

                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
