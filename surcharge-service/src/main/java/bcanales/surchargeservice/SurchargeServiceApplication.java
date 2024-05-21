package bcanales.surchargeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SurchargeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurchargeServiceApplication.class, args);
	}

}
