package com.ipiecoles.java.java240;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:app.properties")
public class SpringConfig {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringConfig.class, args);
	}
	
	@Bean(name="bitcoinServiceWithoutCache")
	public BitcoinService bitcoinServiceWithoutCache(){
		BitcoinService bitcoinService = new BitcoinService();
		bitcoinService.setForceRefresh(true);
		return bitcoinService;
	}
	
	@Bean(name="bitcoinServiceWithCache")
	public BitcoinService bitcoinServiceWithCache(){
		BitcoinService bitcoinService = new BitcoinService();
		bitcoinService.setForceRefresh(false);
		return bitcoinService;
	}
}
