package com.easyparkicesi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${frontend.url}")
    private String frontendUrl;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica para todas las rutas
                .allowedOrigins(frontendUrl)  // Permite solo el origen configurado en application.properties
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos HTTP permitidos
                .allowCredentials(true);  // Permite enviar cookies y credenciales
    }
}
