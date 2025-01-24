package com.hackathon.inditex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InditexApplication {

	public static void main(String[] args) {
		System.setProperty("https.protocols", "TLSv1.2,TLSv1.3");
		System.setProperty("server.servlet.session.cookie.secure", "true");
		System.setProperty("server.servlet.session.cookie.http-only", "true");
		SpringApplication.run(InditexApplication.class, args);
	}

}
