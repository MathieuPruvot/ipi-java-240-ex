package com.ipiecoles.java.java240;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.ipiecoles")
public class SpringConfig {
	
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
