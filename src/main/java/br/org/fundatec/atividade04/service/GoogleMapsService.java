package br.org.fundatec.atividade04.service;

import br.org.fundatec.atividade04.controller.response.GeographicLocation;
import br.org.fundatec.atividade04.controller.response.GoogleMapsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class GoogleMapsService {

    @Value("${app.google-maps.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GoogleMapsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyDgJSB5HA4p8Yuw0zq3qrloSLk79JCodc8&address=Av.%20Jer%C3%B4nimo%20de%20ornelas,%20115,%20porto%20alegre,%20rs
    public Optional<GeographicLocation> getGeographicLocation(String address) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?key=" + apiKey + "&address=" + address + URLEncoder.encode(address, StandardCharsets.UTF_8);

        ResponseEntity<GoogleMapsResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, GoogleMapsResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getFirstLocation();
        }

        return Optional.empty();
    }
}
