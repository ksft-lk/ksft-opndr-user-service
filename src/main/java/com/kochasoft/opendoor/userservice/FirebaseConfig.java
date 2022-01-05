package com.kochasoft.opendoor.userservice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gcp.core.DefaultGcpProjectIdProvider;
import org.springframework.cloud.gcp.core.GcpProjectIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
public class FirebaseConfig {

	@Bean
	public GcpProjectIdProvider project1_IdProvider(@Value("${spring.cloud.gcp.project-id}") String prijectId) {
		return new DefaultGcpProjectIdProvider() {
			@Override
			public String getProjectId() {
				return prijectId;
			}
		};
	}

	public FirebaseConfig(@Value("${firebase-app-key}") String firebaseAppKey) throws IOException {
		InputStream serviceAccount = new ByteArrayInputStream(firebaseAppKey.getBytes());
		FirebaseOptions.Builder builder = FirebaseOptions.builder();
		FirebaseOptions build = builder.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
		FirebaseApp.initializeApp(build);

	}
}
