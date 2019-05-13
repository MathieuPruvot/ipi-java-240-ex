package com.ipiecoles.java.java240;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
	
	@Bean(name="bitcoinServiceWithoutCache")
	public BitcoinService bitcoinServiceWithoutCache(){
		BitcoinService bitcoinService = new BitcoinService();
		bitcoinService.setForceRefresh(true);
		bitcoinService.setWebPageManager(this.webPageManager());
		return bitcoinService;
	}
	
	@Bean(name="bitcoinServiceWithCache")
	public BitcoinService bitcoinServiceWithCache(){
		BitcoinService bitcoinService = new BitcoinService();
		bitcoinService.setForceRefresh(false);
		bitcoinService.setWebPageManager(this.webPageManager());
		return bitcoinService;
	}
	
	@Bean(name="webPageManager")
	public WebPageManager webPageManager(){
		return new WebPageManager();
	}
	
	@Bean(name="produitManager")
	public ProduitManager produitManager(){
		ProduitManager pm = new ProduitManager();
		pm.setWebPageManager(this.webPageManager());
		pm.setBitcoinService(this.bitcoinServiceWithCache());
		return pm;
	}
}
