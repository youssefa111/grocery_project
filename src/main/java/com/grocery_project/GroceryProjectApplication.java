package com.grocery_project;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@SpringBootApplication
public class GroceryProjectApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(GroceryProjectApplication.class, args);
	}

}
