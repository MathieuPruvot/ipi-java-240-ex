package com.ipiecoles.java.java240;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

public class BitcoinService {

    private Double rate = null;

    private Boolean forceRefresh = false;
    
    @Autowired
    private WebPageManager webPageManager;
    
    @Value("${bitcoin.url}")
    private String bitcoinUrl;
    
    public void setWebPageManager(final WebPageManager webPageManager) {
        this.webPageManager = webPageManager;
    }
    
    public void setForceRefresh(final Boolean forceRefresh) {
        this.forceRefresh = forceRefresh;
    }
    
    /**
     * Méthode qui renvoie le cours du Bitcoin
     * @return le cours du bitcoin
     * @throws IOException si impossible d'accéder à la bourse
     */
    public Double getBitcoinRate() throws IOException {
        if(rate != null && !forceRefresh){
            System.out.println("Récupération du cours du bitcoin en cache...");
            return rate;
        }

        System.out.println("Récupération du cours du bitcoin sur site distant");

        String apiResponse = webPageManager.getPageContents(bitcoinUrl);
        apiResponse = apiResponse.replace("{\"EUR\":","");
        apiResponse = apiResponse.replace("}","");
        rate = Double.parseDouble(apiResponse);
        return rate;
    }

    /**
     * Méthode qui renvoie l'équivalent en bitcoin du prix en euro passé en paramètre
     * @param prixEnEuro le prix à convertir
     * @return le prix en bitcoin au taux actuel
     * @throws IOException si impossible d'accéder à la bourse
     */
    public Double getBitcoinPrice(Double prixEnEuro) throws IOException {
        if(rate == null){
            getBitcoinRate();
        }
        return prixEnEuro / rate;
    }

}
