package br.com.tech.challenge.videostorageservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VideostorageserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideostorageserviceApplication.class, args);
	}

}
