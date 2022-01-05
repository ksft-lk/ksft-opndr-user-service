package com.kochasoft.opendoor.userservice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Component
public class FirebaseConfig {

    @Value("${firebase-app-key}")
    String firebaseAppKey;
	
	public FirebaseConfig() {

		FirebaseOptions options = null;
		try {
			InputStream serviceAccount = new ByteArrayInputStream(firebaseAppKey.getBytes());		
			options = new FirebaseOptions.Builder()
			  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
			  .build();
			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
