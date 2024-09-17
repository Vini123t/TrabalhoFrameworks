package br.unipar.tasketracker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests(auth -> auth
                .requestMatchers("/").permitAll()
                .requestMatchers("/login").permitAll()  // Permitir acesso ao login sem autenticação
                .requestMatchers("/tarefas").permitAll()

                .anyRequest().authenticated()
                )
                .formLogin(form -> form.defaultSuccessUrl("/tarefas", true))
                .logout(config -> config.logoutSuccessUrl("/login"))
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}