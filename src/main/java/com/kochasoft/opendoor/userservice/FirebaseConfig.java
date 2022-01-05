package com.kochasoft.opendoor.userservice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Component
public class FirebaseConfig {

    @Value("${firebase-app-key}")
    String firebaseAppKey;
	
	public FirebaseConfig() {
		/*
		 * try { InputStream serviceAccount = new
		 * ByteArrayInputStream(firebaseAppKey.getBytes()); FirebaseOptions.Builder
		 * builder = FirebaseOptions.builder(); GoogleCredentialsProvider
		 * FirebaseOptions build =
		 * builder.setCredentials(GoogleCredentials.fromStream(serviceAccount))
		 * .build(); FirebaseApp.initializeApp(build); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

	}
}
