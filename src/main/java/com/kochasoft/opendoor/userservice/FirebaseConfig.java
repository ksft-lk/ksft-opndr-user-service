package com.kochasoft.opendoor.userservice;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;


@Configuration
public class FirebaseConfig {

	
	@Bean
	public Firestore getFireStore(@Value("${firebase-app-key}")String firebaseAppKey) throws IOException {
			 InputStream serviceAccount = new ByteArrayInputStream(firebaseAppKey.getBytes()); 
			 GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
			 FirestoreOptions build = FirestoreOptions.newBuilder().setCredentials(credentials)
			 .build(); 
			return build.getService(); 
		 

	}
}
