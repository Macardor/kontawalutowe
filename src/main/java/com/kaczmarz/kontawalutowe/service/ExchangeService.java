package com.kaczmarz.kontawalutowe.service;

import com.kaczmarz.kontawalutowe.database.Database;
import com.kaczmarz.kontawalutowe.model.Currency;
import com.kaczmarz.kontawalutowe.model.ExchangeRates;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class ExchangeService {

    public ExchangeService(){

    }

    //exchange rates for given currency to PLN
    public ExchangeRates getExchangeRates(Currency currency) throws IOException {
        JSONObject rates = getExchangeRatesFromApi(currency);
        String ask = rates.get("ask").toString();
        String bid = rates.get("bid").toString();

        return new ExchangeRates(
                currency,
                new BigInteger(bid),
                new BigInteger(ask));
    }

    private JSONObject getExchangeRatesFromApi(Currency currency) throws IOException {
        URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/c/" + currency.toString().toLowerCase() + "/today/");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int responsecode = conn.getResponseCode();

        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {

            StringBuilder inline = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }

            scanner.close();

            JSONObject object = new JSONObject();
            try {
                object = (JSONObject) new JSONParser().parse(String.valueOf(inline));
            } catch (ParseException e) {
                System.out.println("Could not parse to JSONObject");
            }

            JSONArray rates = (JSONArray) object.get("rates");

            return (JSONObject) rates.get(0);
        }
    }
}
