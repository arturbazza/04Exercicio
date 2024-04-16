package br.org.fundatec.atividade04.repository;

import br.org.fundatec.atividade04.model.GeographicLocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeographicLocationRepository extends JpaRepository<GeographicLocationEntity, Long> {
}
