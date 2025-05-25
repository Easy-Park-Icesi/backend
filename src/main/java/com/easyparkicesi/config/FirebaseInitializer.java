package com.easyparkicesi.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;

@Component
public class FirebaseInitializer {
    private static final Logger logger = LoggerFactory.getLogger(FirebaseInitializer.class);

    @PostConstruct
    public void initialize() {
        try {
            // Opción 1: Intenta cargar desde classpath (para desarrollo y JAR)
            InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-config.json");

            // Opción 2: Si no se encuentra, busca en ruta externa (para producción)
            if (serviceAccount == null) {
                String externalConfigPath = "/home/ec2-user/firebase-config.json";
                logger.warn("No se encontró firebase-config.json en classpath. Intentando con: {}", externalConfigPath);
                serviceAccount = new FileInputStream(externalConfigPath);
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://easypark-9ad4e-default-rtdb.firebaseio.com")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("🔥 Firebase inicializado correctamente");
            }
        } catch (Exception e) {
            logger.error("❌ Error inicializando Firebase", e);
            throw new RuntimeException("Falló la inicialización de Firebase", e);
        }
    }
}