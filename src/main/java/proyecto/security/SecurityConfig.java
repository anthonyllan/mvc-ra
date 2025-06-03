package proyecto.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Recursos estáticos públicos
                .requestMatchers(
                    "/registro**",
                    "/js/**",
                    "/img/**",
                    "/webjars/**"
                ).permitAll()
                
                // Páginas públicas
                .requestMatchers("/", "/home", "/logout", "/registro", "/login").permitAll()
                
                // Rutas para EMPLEADO (solo lectura)
                .requestMatchers(
                    "/asis/inicio",
                    "/asis/crear",
                    "/asis/guardar",
                    "/hora/inicio"
                ).hasAnyAuthority("EMPLEADO", "ADMINISTRADOR", "GERENTE")
                
                // Rutas para ADMINISTRADOR (sin eliminación)
                .requestMatchers(
                    "/estado/**",
                    "/per/**",
                    "/tarde/inicio",
                    "/tipo/**",
                    "/asis/editar/**"
                ).hasAnyAuthority("ADMINISTRADOR", "GERENTE")
                
                // Rutas exclusivas para GERENTE
                .requestMatchers(
                    "/depa/**",
                    "/emp/crear",
                    "/emp/editar/**",
                    "/emp/guardar",
                    "/emp/eliminar/**",
                    "/asis/eliminar/**",
                    "/hora/**",
                    "/puesto/**",
                    "/rol/**",
                    "/registro/inicio",
                    "/registro/eliminar/**"
                ).hasAuthority("GERENTE")
                
                // Todas las demás rutas requieren autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .permitAll()
            )
            .logout(logout -> logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );
        
        return http.build();
    }
    
    

    @Bean
    public DaoAuthenticationProvider authenticationProvider(
        UserDetailsService userDetailsService,
        BCryptPasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }
}