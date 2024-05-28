package bcanales.summary1_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Summary1ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Summary1ServiceApplication.class, args);
	}

}
