package bcanales.summary2_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Summary2ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Summary2ServiceApplication.class, args);
	}

}
