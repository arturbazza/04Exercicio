package br.org.fundatec.atividade04.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "geographic_location")
@Data
public class GeographicLocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;

    private Double longitude;
}
