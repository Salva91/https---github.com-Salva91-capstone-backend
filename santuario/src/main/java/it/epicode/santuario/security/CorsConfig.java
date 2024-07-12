package it.epicode.santuario.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Configura l'origine consentita (dove è hostato il tuo frontend Angular)
        configuration.addAllowedOrigin("http://localhost:4200");

        // Configura i metodi HTTP consentiti globalmente
        configuration.addAllowedMethod("*");

        // Configura le intestazioni HTTP consentite globalmente
        configuration.addAllowedHeader("*");

        // Abilita l'invio di credenziali (se necessario, ad esempio se usi i cookie)
        configuration.setAllowCredentials(true);

        // Esponi l'intestazione Authorization
        configuration.addExposedHeader("Authorization");

        // Imposta la cache delle preflight per 1 ora (3600 secondi)
        configuration.setMaxAge(3600L);

        // Configura la source per tutte le richieste
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return new CorsFilter(source);
    }
}