package com.grocery_project.config;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Configuration
public class FirebaseConfig {

//    @Value("${firebase.config.path}")
//    private String firebaseConfigPath;

    @PostConstruct
    public void initialize() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
//		ClassLoader classLoader = FirebaseConfig.class.getClassLoader();
//		File file = new File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
//        System.out.println("file===>>>" + file.getAbsolutePath());
//            FileInputStream serviceAccount =
//                    new FileInputStream("classpath:serviceAccountKey.json");

            Resource resource = new ClassPathResource("serviceAccountKey.json");
            FileInputStream file = new FileInputStream(resource.getFile());
//            InputStream serviceAccount = new FileInputStream(firebaseConfigPath);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(file))
                    .build();
             FirebaseApp.initializeApp(options);
        }

    }

    @Bean
    public Storage storage() throws IOException {
        return StorageOptions.newBuilder().setProjectId("grocery-project-8f6bf").build().getService();
    }
}
