package br.org.fundatec.atividade04.controller;

import br.org.fundatec.atividade04.controller.response.GeographicLocation;
import br.org.fundatec.atividade04.service.GoogleMapsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final GoogleMapsService googleMapsService;

    public AddressController(GoogleMapsService googleMapsService) {
        this.googleMapsService = googleMapsService;
    }

    @GetMapping("/geographic-location")
    public ResponseEntity<GeographicLocation> getGeographicLocation(@RequestParam String address) {
        Optional<GeographicLocation> location = googleMapsService.getGeographicLocation(address);
        return location.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
