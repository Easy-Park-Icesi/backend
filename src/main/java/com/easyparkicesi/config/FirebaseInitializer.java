package com.easyparkicesi.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FirebaseInitializer {
    private static final Logger logger = LoggerFactory.getLogger(FirebaseInitializer.class);

    @PostConstruct
    public void initialize() {
        try {
            InputStream serviceAccount = null;

            // Opción 1: Intenta cargar desde classpath (para desarrollo y JAR)
            serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-config.json");

            if (serviceAccount != null) {
                logger.info("✅ Archivo firebase-config.json encontrado en classpath");
            } else {
                // Opción 2: Busca en diferentes rutas posibles para EC2
                String[] possiblePaths = {
                        "/home/ubuntu/firebase-config.json",  // Para usuario ubuntu
                        "/home/ec2-user/firebase-config.json", // Para usuario ec2-user
                        "./firebase-config.json",              // En directorio actual
                        "/opt/firebase-config.json"            // Ruta alternativa
                };

                for (String path : possiblePaths) {
                    if (Files.exists(Paths.get(path))) {
                        logger.info("✅ Archivo firebase-config.json encontrado en: {}", path);
                        serviceAccount = new FileInputStream(path);
                        break;
                    } else {
                        logger.debug("❌ No se encontró firebase-config.json en: {}", path);
                    }
                }
            }

            if (serviceAccount == null) {
                throw new RuntimeException("No se pudo encontrar firebase-config.json en ninguna ubicación");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://easypark-9ad4e-default-rtdb.firebaseio.com")
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                logger.info("🔥 Firebase inicializado correctamente");
            } else {
                logger.info("🔥 Firebase ya estaba inicializado");
            }

        } catch (Exception e) {
            logger.error("❌ Error inicializando Firebase: {}", e.getMessage());
            throw new RuntimeException("Falló la inicialización de Firebase", e);
        }
    }
}