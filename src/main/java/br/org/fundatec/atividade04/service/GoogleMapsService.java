package br.org.fundatec.atividade04.service;

import br.org.fundatec.atividade04.controller.response.GeographicLocation;
import br.org.fundatec.atividade04.controller.response.GoogleMapsResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class GoogleMapsService {

    @Value("${app.google-maps.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public GoogleMapsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<GeographicLocation> getGeographicLocation(String address) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?key=" + apiKey + "&address=" + address;

        ResponseEntity<GoogleMapsResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, GoogleMapsResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody().getFirstLocation();
        }

        return Optional.empty();
    }
}