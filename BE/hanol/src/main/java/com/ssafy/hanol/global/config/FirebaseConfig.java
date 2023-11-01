package com.ssafy.hanol.global.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Configuration
@Slf4j
public class FirebaseConfig {

    @Value("${firebase.sdk.path}")
    private String firebaseSdkPath;

    private FirebaseApp firebaseApp;

    @PostConstruct
    public FirebaseApp initializeFCM() {
//        try {
            Resource resource = new ClassPathResource(firebaseSdkPath);
            InputStream serviceAccount = resource.getInputStream();
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
            firebaseApp = FirebaseApp.initializeApp(options);
            return firebaseApp;
//        } catch (FileNotFoundException e) {
//            log.error("Firebase ServiceAccountKey FileNotFoundException" + e.getMessage());
//        } catch (IOException e) {
//            log.error("FirebaseOptions IOException " + e.getMessage());
//        }
    }

//    @Bean
//    public FirebaseAuth initFirebaseAuth() {
//        FirebaseAuth instance = FirebaseAuth.getInstance(firebaseApp);
//        return instance;
//    }
//
//    @Bean
//    public FirebaseMessaging initFirebaseMessaging() {
//        FirebaseMessaging instance = FirebaseMessaging.getInstance(firebaseApp);
//        return instance;
//    }

}
