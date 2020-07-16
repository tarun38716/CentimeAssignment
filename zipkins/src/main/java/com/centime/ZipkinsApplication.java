package com.centime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin2.server.internal.EnableZipkinServer;


@SpringBootApplication
@EnableZipkinServer
public class ZipkinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinsApplication.class, args);
	}

}
